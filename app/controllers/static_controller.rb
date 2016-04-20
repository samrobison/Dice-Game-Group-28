class StaticController < ApplicationController
	def stats
		@players = User.all.sort { |x,y| y.wins <=> x.wins }
	end
end
