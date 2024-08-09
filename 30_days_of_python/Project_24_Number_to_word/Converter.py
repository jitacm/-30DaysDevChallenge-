def number_to_words(num):
    ones = ["", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"]
    teens = ["", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"]
    tens = ["", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"]
    thousands = ["", "Thousand", "Million", "Billion"]

    def word(num):
        if num == 0:
            return ""
        elif num < 10:
            return ones[num] + " "
        elif num < 20:
            return teens[num - 10] + " "
        elif num < 100:
            return tens[num // 10] + " " + ones[num % 10] + " "
        else:
            return ones[num // 100] + " Hundred " + word(num % 100)

    if num == 0:
        return "Zero"

    result = ""
    index = 0

    while num > 0:
        if num % 1000 != 0:
            result = word(num % 1000) + thousands[index] + " " + result
        num //= 1000
        index += 1

    return result.strip()

number = int(input("Enter a number: "))
print(number_to_words(number))
