//go:generate mockgen
package main

import (
	"fmt"
	"strconv"
)

func runLengthEncode(rle string) string {
	count := 0
	lastChar := ' '
	result := ""
	for _, currentChar := range rle {
		if currentChar != lastChar && count > 0 {
			result += strconv.Itoa(count) + string(lastChar)
			count = 0
		}
		lastChar = currentChar
		count++
	}
	if count > 0 {
		result += strconv.Itoa(count) + string(lastChar)
	}
	fmt.Println("RLE: ", rle, " Res: ", result)
	return result
}

func countAndSay(n int) string {
	if n == 1 {
		return "1"
	}

	rle := countAndSay(n - 1)
	return runLengthEncode(rle)
}

func main() {
	fmt.Println("-------")
	countAndSay(4)
}
