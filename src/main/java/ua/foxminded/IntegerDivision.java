package ua.foxminded;

public class IntegerDivision {
    private final StringBuilder result = new StringBuilder();
    private final StringBuilder quotient = new StringBuilder();
    private final StringBuilder reminder = new StringBuilder();

    public String getIntegerDivision(int dividend, int divisor) {
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor can't be 0");
        }

        if (dividend < divisor) {
            throw new IllegalArgumentException("Dividend can't be less than divisor");
        }

        String[] digits = String.valueOf(dividend).split("");
        int reminderNumber;
        int multiplyResult;
        int mod;

        for (int i = 0; i < digits.length; i++) {
            reminder.append(digits[i]);
            reminderNumber = Integer.parseInt(reminder.toString());

            if (reminderNumber >= divisor) {
                mod = reminderNumber % divisor;
                multiplyResult = reminderNumber / divisor * divisor;

                String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber);
                result.append(lastReminder).append("\n");

                String multiply = String.format("%" + (i + 2) + "d", multiplyResult);
                result.append(multiply).append("\n");

                Integer tab = lastReminder.length() - calculateDigit(multiplyResult);
                result.append(createDivisor(multiplyResult, tab)).append("\n");

                quotient.append(reminderNumber / divisor);

                reminder.replace(0, reminder.length(), Integer.toString(mod));
                reminderNumber = Integer.parseInt(reminder.toString());
            }

            if (i == digits.length - 1) {
                result.append(String.format("%" + (i + 2) + "s", reminderNumber)).append("\n");
            }
        }

        modifyResultView(dividend, divisor);
        return result.toString();
    }

    public String createDivisor(Integer multiplyResult, Integer tab) {
        return assemblyString(tab, ' ') + assemblyString(calculateDigit(multiplyResult), '-');
    }

    public int calculateDigit(int i) {
        if (i == 0) {
            return 1;
        }

        return (int) Math.log10(i) + 1;
    }

    public String assemblyString(int numberOfSymbols, char symbol) {
        if (numberOfSymbols < 0) {
            throw new IllegalArgumentException("Number of symbols cannot be negative");
        }

        StringBuilder resultingString = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            resultingString.append(symbol);
        }
        return resultingString.toString();
    }


    public void modifyResultView(Integer dividend, Integer divisor) {
        int[] index = new int[3];

        int j = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '\n') {
                index[j] = i;
                j++;

                if (j == 3) {
                    break;
                }
            }
        }

        int tab = calculateDigit(dividend) + 1 - index[0];
        result.insert(index[2], assemblyString(tab, ' ') + "│" + quotient.toString());
        result.insert(index[1], assemblyString(tab, ' ') + "│" + assemblyString(quotient.length(), '-'));
        result.insert(index[0], "│" + divisor);
        result.replace(1, index[0], dividend.toString());
    }
}
