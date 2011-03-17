import System.Environment

-- The main calculation functions
data ChangeItem = Item Denomination Int

instance Show ChangeItem where
  show (Item denomination quantity) = show quantity ++ " x " ++ show denomination

calculateChange :: Float -> Float -> (Float, [ChangeItem])
calculateChange given required | given >= required = (convertToPounds changeAmount, workOutChangeFor changeAmount)
                               | otherwise         = error "Amount given is less than required" 
                                                     where changeAmount = convertToPence given - convertToPence required

workOutChangeFor :: Int -> [ChangeItem]
workOutChangeFor amount = reverse (items (foldl addDenomination (Total amount []) denominations))

convertToPounds :: Int -> Float
convertToPounds amount = (fromIntegral amount) / 100.0

convertToPence :: Float -> Int
convertToPence amount = truncate (amount * 100)

-- Definition of types and functions for calculating the change total
data ChangeTotal = Total Int [ChangeItem]

instance Show ChangeTotal where
  show (Total amount items) = "Remaining: " ++ show amount ++ ", items: " ++ show items

remaining :: ChangeTotal -> Int
remaining (Total amount _) = amount

items :: ChangeTotal -> [ChangeItem]
items (Total _ changeItems) = changeItems


addDenomination :: ChangeTotal -> Denomination -> ChangeTotal
addDenomination total denomination | remaining total < value denomination = total
                                   | otherwise                            = accumulate total denomination
                                               
accumulate :: ChangeTotal -> Denomination -> ChangeTotal
accumulate total denomination = Total (remaining total - (quantity * value denomination)) 
                                      (Item denomination quantity : items total)
                                where quantity = truncate (fromIntegral (remaining total) / fromIntegral (value denomination))

-- Definition of the different denominations that change can be distributed in
data Denomination = Note Int String | Coin Int String

instance Show Denomination where
	show (Note _ display) = display
	show (Coin _ display) = display

denominations :: [Denomination]
denominations = [Note 5000 "£50", Note 2000 "£20",  Note 1000 "£10", Note 500 "£5", Coin 200 "£2", Coin 100 "£1",
                 Coin 50 "50p", Coin 20 "20p", Coin 10 "10p", Coin 5 "5p", Coin 2 "2p", Coin 1 "1p"]

value :: Denomination -> Int
value (Note val _) = val
value (Coin val _) = val              

-- Display oriented functions
stringsWithNewlines :: [String] -> String
stringsWithNewlines = foldl (\acc s -> acc ++ s ++ "\n") ""

calculateAndDescribeChange :: Float -> Float -> String
calculateAndDescribeChange given required = "The required change is: £" ++ show amount ++ 
                                            ". Made up of:\n" ++ stringsWithNewlines (map (\item -> show item) items)
                                            where (amount, items) = calculateChange given required

main = do   
	(given:required:args) <- getArgs
	putStrLn (calculateAndDescribeChange (read given :: Float) (read required :: Float))
