package bankmicroservicesapp.passhelper;

import java.util.List;
import java.util.UUID;

class PassHelper {
    public static void main(String[] args) {

        List<String> passList = List.of("9A#k2sF!zL" +
                        "P6@vRdW3yH",
                "Xn8*GtQ7jE",
                "M4$zUyP1wT",
                "B2%rZvK5xQ",
                "7L*qKdRi6O",
                "T3#wHfY9mZ",
                "F5&xPvD2cJ",
                "W1%mGjX8nB",
                "H6@tLzA4vE",
                "O9*rCmPq7S",
                "V2&wEgRf3Y",
                "I4#sBpU8oN",
                "J8%kMvTz1X",
                "5S*hQdW7rL",
                "Z3$cNfG2aP",
                "6T#vYsK9wJ",
                "R1&pZxV7iD",
                "E7%nXmFy4Q",
                "U2*aLQW5tG");
        for (String s : passList) {
            UUID uuid = UUID.nameUUIDFromBytes(s.getBytes());
            System.out.println(uuid.toString());
        }
    }
}
