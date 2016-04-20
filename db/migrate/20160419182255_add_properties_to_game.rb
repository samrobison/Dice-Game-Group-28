class AddPropertiesToGame < ActiveRecord::Migration
  def change
    add_column :games, :scores, :text
    add_column :games, :dice, :text
  end
end
