class GamesController < ApplicationController

	def new
		flash.clear
		@game = Game.new
	end

	def index
		@games = current_user.games.sort { |x,y| y.updated_at <=> x.updated_at }
	end

	def create
		

		humans = params[:humans].split(',')

		@game = Game.new(name: params[:game][:name], players: params[:game][:players].to_i + 1, humans: humans.size + 1, call: 0,round: 1,turn: 1, history:"" )
		
		#checks if games name is in use
		if Game.where(name: params[:game][:name]).count  != 0
			flash[:danger] = "Name is in use" 
			render 'new'
			return
		end

		#check if all the human players exist and are actually human
		humans.each do |hum|
			person =  User.where(email: hum.strip).first
			if person and person.human
				@game.users.push(person)

			else
				 flash[:danger] = "#{hum} is not a valid user" 
				render 'new'
				return
			end
		end

		#adds computers to fill the empty spaces
		(@game.players - humans.count - 1).times do |index|
			@game.users.push(User.where(email: "cpu#{index}").first)
		end
		@game.users.push(current_user)
		@game.turn = @game.users.index(current_user)

		#set up empty scores
		@game.users.each do |play|
			@game.scores[play.email] = 0
		end
		
		@game.players.times do |index|
			@game.dice.push((rand(5) +1) + (rand(5) +1) + (rand(5) +1))
		end

		

		if @game.save!

			

			#add this game to humans game lists
			humans.each do |hum|
				use = User.where(email: hum.strip).first
				use.games.push(@game)
				use.save!
			end
			current_user.games.push(@game)
			current_user.save!

			redirect_to @game
		else
			flash[:danger] = "Invalid name or number of players" 
			render 'new'
			return
		end

	end

	def show
		unless checkPermissions(params[:id])
			redirect_to root_path
		end
		@total = 0
		@myTurn = false
		@turn = 0
		@winners = Array.new()


		@game = Game.find(params[:id])
		@players = @game.users
		@scores = @game.scores

		if @game.round < 4
			@total = @game.dice[@game.users.index(current_user)]
			@myTurn = (@turn == current_user)
			@turn = @players[@game.turn]
		else
			@winners = findWinners(@game)
		end
		

	end


	def update

		game = Game.find(params[:id])
		if params[:game][:call].to_i == -1
			game.history += "#{current_user.email} called a bluff \n"
			checkBluff(game)
		else
			game.history += "#{current_user.email} made a call of #{params[:game][:call]}\n"
			game.update(call: params[:game][:call])
			game.scores[current_user.email] += game.call
			game.save!

			#set turn to next player
			if game.turn == game.players - 1
				game.update(turn: 0)
			else
				game.update(turn: game.turn + 1)
			end

			turn = game.users[game.turn]
			round = game.round
			
			while turn.human == false and game.round == round and round < 4
				AITurn(turn, game)
				turn = game.users[game.turn]
			end

		end
		redirect_to game
	end

private

	def checkBluff(game)
		caller = game.users[game.turn]
		callie =  game.users[game.turn-1]

		if game.turn == 0
			callie = game.users[game.players - 1]
		end
		game.history += "#{caller.email} called #{callie.email} on bluffing\n"

		#bluff callled correctly
		if game.dice[game.users.index(callie)] < game.call
			#reduce the bluffer's score by the amount they called
			game.scores[callie.email] -= game.call * 2
			game.scores[callie.email] = 0 if game.scores[callie.email] < 0
			# increase the bluff callers score by the amount called
			game.scores[caller.email] += game.call
			game.history += "#{caller.email}'s call was correct\n "
		#bluff called incorrectly
		else
			#increase bluffers score by call * 3/2
			game.scores[callie.email] += (game.call * 3/2).to_i
			#decres bluff callers score by call
			game.scores[caller.email] -= game.call
			game.scores[caller.email] = 0 if game.scores[caller.email] < 0
			game.history += "#{caller.email}'s call was incorrect \n"
		end

		game.save!
		nextRound(game)
	end

	def AITurn(cpu, game)
		game.history += "\n#{cpu.email}'s turn \n"
		game.save!
		#these are for making conversion from java easier
		currentBid = game.call
		score = game.dice[game.users.index(cpu)]

		if currentBid >= score && currentBid >= 15
			return checkBluff(game)
		elsif currentBid >= score && currentBid <= 9
			game.update(call: currentBid + rand(3) + 1)
		elsif currentBid >= score && (currentBid - score) > 3
			return checkBluff(game)
		elsif currentBid >= score
			game.update(call: currentBid + rand(3) + 1)
		elsif currentBid < score && (score - currentBid) < 5
			game.update(call: score)
		elsif currentBid <= score && (score - currentBid) >= 5
			game.update(call: currentBid + rand(4) + 1)
		else
			return checkBluff(game)
		end
		game.scores[cpu.email] += game.call
		game.history += "#{cpu.email} made a call of #{game.call}\n"
		game.save!


		if game.turn == game.players 
			game.update(turn: 0)
		else
			game.update(turn: game.turn + 1)
		end
	end

	def nextRound(game)
		game.update(turn: 0, call: 0, round: game.round + 1)
		game.users = game.users.shuffle
		game.dice.count.times do |index|
			game.dice[index] = (rand(5) +1) + (rand(5) +1) + (rand(5) +1)
		end
		game.save!

		turn = game.users[game.turn]
		round = game.round

		if round == 4
			endGame(game)

		else

		end
			
		while turn.human == false and game.round == round and round < 4
			AITurn(turn, game)
			turn = game.users[game.turn]
		end

	end

	def findWinners(game)
		winner = game.users[0]

		game.users.each do |user|
			if game.scores[user.email] > game.scores[winner.email]
				winner = user
			end
		end

		winners = Array.new

		game.users.each do |user|
			if game.scores[user.email] == game.scores[winner.email]
				winners.push(user)
			end
		end
		return winners

	end

	def endGame(game)
		winners = findWinners(game)
		game.history += "Game over \nWinner(s) are: "
		game.users.each do |user|
			if winners.index(user)
				user.update(wins: user.wins + 1)
				game.history += "#{user.email} "
			else
				user.update(looses: user.looses + 1)
			end
		end
		game.save!

	end



	def checkPermissions(id)
		game = Game.find(id)

		return false if current_user == nil
		
		game.users.each do |user|
			return true if current_user.id == user.id
		end

		return false
	end

end
