

Anna Breck    breck061

I often made the assumption that by neighbors you meant only adjacent (up, down, left, right) and not including diagonal neighbors.

 - FLAGS ARE ABLE TO BE REMOVED! just input r when asked for a flag and it will remove if there is a flag there. It also updates the
guess and mines found count! I utilized my int[][] intField (dissuaded later) to accomplish this.
 - For this project i did implement something special, just like the real minesweeper the revealZeros() has a helper that reveals all the
edges of the 0 shape.
 - When ending the game i implemented a printEnd() function that makes the correctly placed flags red and the incorrect ones yellow
 - Also, if you loose the game it gives you a count of the number of mines you revealed with flags.
 - There are several helper functions to help with stack and queue functions.
 - I used an additional double array (int[][] intField) to keep track of the amount of bombs near squares, essentially keeping a
number copy of the field.
 - I ended up using stack instead of queue for evaluate mines due to my int[][] array.
 - Since there were so many verifications of bounds i made a helper function that appears in pretty much every function block
called verifyIndices() that compares x and y coordinates to the bounds of the array
 - I couldnt figure out how to get an array back for a stack i just separated the coordinate pairs and reversed them when popping
e.x. push(x); push(y); => y = pop(); x = pop(); this worked actually really well
 - I also messed with the colors to make the game prettier :)
 - gameOver() has lots of global variables to help it not be so long, for instance a flag count, guess count, and a minerevealed count.
these could be used to trigger an instant game end instead of looping through until mines are found it just returns if the amount of
guesses or guessed mines equal the amount of mines.
 - isOver was implemented as a global variable in main to be used to trigger end of the game if a true came from guess(), which trues only
appear if a mine is revealed.
 - convert() is its own function because it is used to convert my integer representation of the field to a Cell representation
 - revealBoard() shows you the board after :) thought it was a cool feature to have
 - I am extremely happy with my game end and how it reveals everything and prints the board! It used to have a bug where
nothing above three would print messing up the board, but i fixed it!


My friend gave me the amazing idea of putting 0s before single digit numbers to make it line up better and i love it