class User < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
          :rememberable

  has_many :games, class_name: "Game"

  belongs_to :game, class_name: "Game"
end
