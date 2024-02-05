package cinema.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

/*
Stage 1/4: The show begins

Let's make our virtual movie theater with the help of a REST service. Our movie theater has 9 rows with 9 seats each.
 In this stage, you need to create a simple endpoint that will return the information about the cinema in JSON format.

Objectives
Implement the /seats endpoint that handles GET requests and returns the information about the movie theatre.

The response should contain information about the rows, columns, and available seats.
The response is a JSON object and has the following format:

{
   "rows": 5,
   "columns": 6,
   "seats": [
      {
         "row": 1,
         "column": 1
      },

      ........

      {
         "row": 5,
         "column": 5
      },
      {
         "row": 5,
         "column": 6
      }
   ]
}
Our cinema room has 9 rows with 9 seats each, so the total number of respective rows and columns also amounts to 9 each.

Note that the seats array contains 81 elements, as there are 81 seats in the room.

Example
1: a GET /seats request
Response body:

{
   "rows": 9,
   "columns": 9,
   "seats": [
      {
         "row": 1,
         "column": 1
      },
      {
         "row":1,
         "column":2
      },
      {
         "row": 1,
         "column": 3
      },

      ........

      {
         "row": 9,
         "column": 8
      },
      {
         "row": 9,
         "column": 9
      }
   ]
}
 */
public class Seat {
    private final String token;
    private final Ticket ticket;

    private boolean isSeatBooked;

    public Seat(int row, int column) {
        this.ticket = new Ticket(row, column);
        this.token = generateToken();
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getToken() {
        return token;
    }


    private String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @JsonIgnore
    public boolean isSeatBooked() {
        return isSeatBooked;
    }

    public void setSeatBooked(boolean seatBooked) {
        isSeatBooked = seatBooked;
    }
}
