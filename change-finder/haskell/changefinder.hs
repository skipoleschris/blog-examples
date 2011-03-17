import System.Environment

type ChangeItem = (Denomination, Int)
type Denomination = (Int, String)
type ChangeTotal = (Int, [ChangeItem])

calculateChange :: Float -> Float -> (Float, [ChangeItem])
calculateChange given required | given >= required = (convertToPounds changeAmount, workOutChangeFor changeAmount)
                               | otherwise         = error "Amount given is less than required" 
                                                     where changeAmount = convertToPence given - convertToPence required

workOutChangeFor :: Int -> [ChangeItem]
workOutChangeFor amount = reverse (snd (foldl includeDenominationIfPossible (amount, []) denominations))

includeDenominationIfPossible :: ChangeTotal -> Denomination -> ChangeTotal
includeDenominationIfPossible acc denomination | fst acc < fst denomination = acc
                                               | otherwise                  = accumulateDenomination acc denomination
                                               
accumulateDenomination :: ChangeTotal -> Denomination -> ChangeTotal
accumulateDenomination (amt, items) denomination = (amt - (quantity * fst denomination), (denomination, quantity) : items)
                                                   where quantity = truncate (fromIntegral (amt) / fromIntegral (fst denomination))

convertToPounds :: Int -> Float
convertToPounds amount = (fromIntegral amount) / 100.0

convertToPence :: Float -> Int
convertToPence amount = truncate (amount * 100)

denominations :: [Denomination]
denominations = [(5000, "£50"), (2000, "£20"), (1000, "£10"), (500, "£5"), (200, "£2"), (100, "£1"),
                 (50, "50p"), (20, "20p"), (10, "10p"), (5, "5p"), (2, "2p"), (1, "1p")]


-- Display oriented functions

changeItemsToStrings :: [ChangeItem] -> [String]
changeItemsToStrings = map (\(d, qty) -> show qty ++ " x " ++ snd d)

stringsWithNewlines :: [String] -> String
stringsWithNewlines = foldl (\acc s -> acc ++ s ++ "\n") ""

calculateAndDescribeChange :: Float -> Float -> String
calculateAndDescribeChange given required = "The required change is: £" ++ show (fst change) ++ 
                                            ". Made up of:\n" ++ stringsWithNewlines (changeItemsToStrings (snd change))
                                            where change = calculateChange given required

main = do   
	(given:required:args) <- getArgs
	putStrLn (calculateAndDescribeChange (read given :: Float) (read required :: Float))
