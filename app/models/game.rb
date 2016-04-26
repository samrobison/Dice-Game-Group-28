class Game < ActiveRecord::Base

	include ActiveModel::Serialization

	serialize :scores, Hash
	serialize :dice, Array
	has_many :users, class_name: "User"
	belongs_to :user, class_name: "User"
	
	validates :name, presence: true
	validates :players, presence: true, numericality: {less_than: 10, greater_than: 1}
	validates :humans, presence: true, numericality: {less_than: 10, greater_than: 0}


	

	
end
