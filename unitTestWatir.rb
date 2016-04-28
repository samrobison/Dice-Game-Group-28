require "watir"
require 'watir-webdriver'
require 'webdriver-user-agent'



user = ["blah@blah.com","asdf"]


puts "Starting unit test"
#test sign in 
browser = Watir::Browser.new 
browser.goto 'http://159.203.222.152:3000/users/sign_in'
browser.text_field(id: "user_email").set user[0]
browser.text_field(id: "user_password").set user[1]
browser.button(name: "commit").click

if browser.url == "http://159.203.222.152:3000" or browser.url == "http://159.203.222.152:3000/" 
	puts "Sign in test passed"

	#test game create game
	10.times do |num|

		browser.goto 'http://159.203.222.152:3000/games/new'
		browser.text_field(id: "game_name").set "game#{rand(1000)} #{rand(1000)} #{rand(1000)}"
		browser.select_list(id: "game_players").select_value("3")
		browser.button(name: "commit").click

		unless browser.url == "http://159.203.222.152:3000/games"
			#test game play with only calls
			puts "Create game #{num} test passed "

			while (browser.html =~ /Game over/) == nil
				browser.button(name: "commit").click
				sleep(2)
			end
			#getting to the end of that loop means the test passed
			puts "Call test #{num} passed"

			#test with bluffs 

			browser.goto 'http://159.203.222.152:3000/games/new'
			browser.text_field(id: "game_name").set "game#{rand(1000)} #{rand(1000)} #{rand(1000)}"
			browser.select_list(id: "game_players").select_value("3")
			browser.button(name: "commit").click

			while (browser.html =~ /Game over/) == nil
				 if (browser.html =~ /Current call: 0/) == nil
				 	browser.radio(:name => 'action', :value => 'bluff').set
				 end

				browser.button(name: "commit").click
				sleep(2)
			end
			#getting to the end of that loop means the test passed
			puts "Bluff test#{num} passed"
			
		end
	end

else
	puts "failed sign in test"
end