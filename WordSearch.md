Word Search 

/capabilities

The word search app (hence to be referred to as WS) will be capable of generating a 
puzzle of variant height and width, as defined by the user within certain constraints 
(e.g. the puzzle cannot be a single letter tall or wide).  WS will be able to take a 
user-defined grid (again, within certain constraints) and generate a user-defined 
number of words inside said grid.  These words can be generated horizontally, vertically, 
or diagonally (either angled upward or downward).  WS will not support generating words 
backwards (i.e. 'sentence' as 'ecnetnes'), as this is counter-intuitive and hard to read
for the user.  WS will also support "criss-crossing" words, i.e. words that intersect each
other at shared letters (e.g. 'fort' displayed vertically would be able to intersect with 
'moat' displayed horizontally, intersecting at the letter 'o'). 

WS will take the following steps to return a JSON string detailing a list of its 
capabilities.

1. Create a new capability object within the program for each capability listed above.

	1.1. Each of these objects will have the following details:
	
		1.11. Name: the name of the capability
		
		1.12. Description: a description of the capability
		
		1.13. Keyword: a keyword which denotes the capability 
		
2. Create a larger object which holds all of the capabilities within a list. 

3. Convert the list of objects to JSON.    

/puzzle

As detailed above, WS will have the capability to generate a word search puzzle 
consisting of a random selection of words surrounded by a random selection of letters. 

After inserting a random selection of words from an English dictionary into
a user-defined grid, WS will then fill every remaining blank square within the grid with a
random letter of the English alphabet. 

WS will be capable of handling difficult requests such as the following:

1. A puzzle of size 2x2, with 500 words that are 5 letters minimum, 10 letters maximum, 
and words that can be generated horizontally, vertically, diagonally-upwards and 
diagonally-downwards.  This request will be unfulfilled due to the impossibility of the
request's dimensions. The JSON request for this is as follows:

	1.1. 
	{
    "width": 2,
    "height": 2,
    "words": 500,
    "minLength": 5,
    "maxLength": 10,
    "capabilities": ["horizontal", "vertical", "diagonal-up", "diagonal-down"]
	}
	
2. A puzzle of size 50x50, with 1 word that is 20 letters minimum, 4000 letters maximum, 
and can be generated only vertically. The JSON request for this is as follows:

	2.1. 
	{
    "width": 50,
    "height": 50,
    "words": 1,
    "minLength": 20,
    "maxLength": 40,
    "capabilities": ["vertical"]
	}

List of steps in plain English: 
1. Define grid
2. Define height of grid
3. Set height of grid
4. Define width of grid
5. Set width of grid
6. Draw left wall of grid (by height)
7. Draw bottom wall of grid (by width)
8. Draw right wall of grid (by height)
9. Draw top wall of grid (by width)
10. Draw vertical lines inside of grid (by width, e.g. if width is 6 then draw 6 
	evenly-spaced vertical lines.
11. Draw horizontal lines inside of grid (by height, e.g. if height is 6 then draw 6 
	evenly-spaced horizontal lines.
12. Define number of words in puzzle
13. Set number of words in puzzle
14. Define list of words(WList) to be in puzzle
15. Define minimum length of words to be in puzzle
16. Set minimum length of words to be in puzzle
17. Define maximum length of words to be in puzzle
18. Set maximum length of words to be in puzzle
19. Select words at random from dictionary (under constraints described in steps 12-18)
	until list of words is filled. 
20. Define list of capabilities(CList), these describe the directions in which words can be 
	written. 
21. Add capabilities to list of capabilities (e.g. "horizontal" or "diagonal-upwards")
22. Select the first word from the WList and assign a capability from CList at random to it
23. Pick random square in puzzle (making sure that the word can fit in the puzzle grid, 
given its capability, if it starts from that square) and write word in puzzle grid in 
all upper-case letters.  If the word overlaps another word already in the puzzle grid, 
ensure that the letter at which the two words cross matches
24. Repeat steps 22 and 23 until all words from WList are written in puzzle grid
25. Fill all empty squares in puzzle grid with random letters from the English Latin
alphabet
26. Write WList below puzzle

To generate a puzzle, WS will take the following into account.

1. Word Generation 

	1.1. List of Constraints
	
		1.11. Checks if the total number of characters in the word selection exceeds the 
		total possible supported number of characters of the puzzle.  If the first number 
		(re: selection) exceeds the second (re: puzzle size), the selection generation 
		stops.
		
		1.12. Checks if each word to be added to the selection is bigger than the size of 
		the puzzle; if so, the word is not added.  
			1.121. This check will use the diagonal capability mentioned above to check 
			if a word will exceed the bounds of the puzzle diagonally.  
			
		1.13. Checks if each word meets the user-defined constraints; if not, the word
		is not added.  
		
		1.14. Checks if each word has already been added to the selection; if so, the word
		is not added. 
		
		1.15. Checks if each word conforms to user-generated constraints (e.g. max. length
		of word). 
		
2. "Criss-crossing" words

	2.1. Definition: a criss-crossing (CC) word is defined as a word that intersects 
	another word, at a character which both words share.  
		
		2.11. Example:
		
				F
			   MOAT
				R
				T
				
	2.2. CC words will be handled when words are written into the puzzle grid.  Words will
	be written letter by letter according to the rules which that word's capability 
	describes.  If a grid square is reached which already contains a letter, WS checks 
	to see if that letter matches the letter about to be written.  If they match, the WS
	continues to write the word into the puzzle.  If they do not match, WS moves backwards
	and erases each letter of that word. 
	
3. Data structures

	3.1. WS will contain a number of key data structures, which are detailed here.
	
		3.11. Word set: contains the words which are written into the puzzle grid. This
		will also be displayed beneath the puzzle itself (this may be handled on the 
		iOS side). 
		
		3.12. Capabilities set: contains the possible capabilities which can be assigned
		to words. This will either be a property of the puzzle or contained within the 
		main method.  
		
		3.13. Puzzle array: this is a two-dimensional array (matrix) which contains all 
		words from the word set as well as a random selection of letters from the English 
		Latin alphabet. 
		
		3.14. User-defined constraints: this is a list of user-defined constraints for the 
		puzzle (e.g. puzzle grid height).  This will be a property of the puzzle itself. 
		
	3.2. WS will also include a puzzle-generation method.  This method will be separate 
	from the main method.  
	
4. Additional concerns

	4.1. JSON parsing: WS will also need a method for deserializing (parsing) received 
	user-defined capabilities from JSON format, as well as a method for serializing 
	generated puzzles to JSON format. 
	
	4.2. A spark framework will be needed to use endpoints. 
	
	4.3. A condition for handling "failure states" during word inscription will be 
	paramount.  Does the method reverse course and delete the letters it has already 
	inscribed, or does the method attempt to "see into the future" and check if a word
	already exists in the space in which it is to be inscribed before such inscription 
	occurs? 
	
	4.4. Our inscription method must convert all letters to upper-case.  
	
	4.5. "Common sense" constraints will be necessary for puzzle generation.  We can't
	allow for puzzles that are a single unit tall or wide, or for puzzles that are too
	full of words. 
		
		
		
		
