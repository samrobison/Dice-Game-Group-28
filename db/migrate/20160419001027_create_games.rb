class CreateGames < ActiveRecord::Migration

  def change
    create_table :games do |t|
    	t.string 	:name
    	t.integer :players
    	t.integer :humans
    	t.integer :call
    	t.integer :round
    	t.integer	:turn
      t.integer :game_id
      t.integer :user_id
      t.text    :history

      t.timestamps null: false
    end
  end
end
