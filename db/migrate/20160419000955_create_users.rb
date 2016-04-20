class CreateUsers < ActiveRecord::Migration
  def change
    create_table :users do |t|
      t.boolean :human, :default => true
      t.boolean :inGame, :default => false
      t.integer :wins, :default => 0
      t.integer :looses, :default => 0
      t.integer :user_id
      t.integer :game_id
      
      t.timestamps null: false
    end
  end
end
