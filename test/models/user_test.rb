require 'test_helper'

class UserTest < ActiveSupport::TestCase

  
	def setup
		@user = User.new(email: "fake@fake.com")
		@user.password = "asdfasdf"
    	@user.password_confirmation = "asdfasdf"
	end

	test "user should save" do
    	assert @user.valid?
  	end

  	#i turned off all validations so users can do what ever they want
  	test "user should save with different confirmations" do
  		@user.password_confirmation = "notthesame"
    	assert @user.valid?
  	end

  	test "database doesn't care about name format" do
  		@user.email = "asjlafldksasdflkadfjkskajlsf"
    	assert @user.valid?
  	end

  	test "increment wins" do
  		@user.wins += 1
  		assert @user.valid?
  	end

  	test "increment looses" do
  		@user.looses += 1
  		assert @user.valid?
  	end

  	


  # test "the truth" do
  #   assert true
  # end
end
