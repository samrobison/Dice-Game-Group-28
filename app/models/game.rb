class Game < ActiveRecord::Base

	include ActiveModel::Serialization

	serialize :scores, Hash
	serialize :dice, Array
	has_many :users, class_name: "User"
	belongs_to :user, class_name: "User"
	
	validates :name, presence: true
	validates :players, presence: true

	

	
end
