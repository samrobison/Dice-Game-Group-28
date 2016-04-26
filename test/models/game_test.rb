require 'test_helper'

class GameTest < ActiveSupport::TestCase
 	def setup
		@game = Game.new(name: "fake")
	end

	test "test presence of number of users" do
    	assert_not @game.valid?
  	end

  	test "test lower limit of players" do
  		@game.players = 1
    	assert_not @game.valid?
  	end

  	test "test upper limit of players" do
  		@game.players = 10
    	assert_not @game.valid?
  	end

  	test "test upper limit of humans" do
  		@game.players = 10
    	assert_not @game.valid?
  	end

  	test "test lower limit of humans" do
  		@game.players = 0
    	assert_not @game.valid?
  	end

  	test "test valide game" do
  		@game.players = 3
  		@game.humans = 1
    	assert @game.valid?
  	end

  	test "test absence of name" do
  		@game.name = nil
    	assert_not @game.valid?
  	end
end
