import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class AviaSoulsTest {
    @Test
    public void shouldCompareToTickets() {
        Ticket ticket1 = new Ticket("Тюмень", "Москва", 4_000, 19_00, 22_00);
        Ticket ticket2 = new Ticket("Москва", "Санкт-Петербург", 3_000, 12_00, 13_00);
        Ticket ticket3 = new Ticket("Санкт-Петербург", "Тюмень", 5_000, 15_00, 17_00);
        Ticket ticket4 = new Ticket("Тюмень", "Москва", 4_000, 19_00, 22_00);

        Assertions.assertTrue(ticket1.compareTo(ticket2) > 0);
        Assertions.assertTrue(ticket2.compareTo(ticket3) < 0);
        Assertions.assertTrue(ticket1.compareTo(ticket3) < 0);
        Assertions.assertTrue(ticket1.compareTo(ticket4) == 0);
    }


    @Test
    public void flightTimeComparatorTest() {
        Ticket ticket1 = new Ticket("Тюмень", "Москва", 4_000, 19_00, 22_00);//3 часа
        Ticket ticket2 = new Ticket("Москва", "Санкт-Петербург", 3_000, 12_00, 13_30);//1,5 часа
        Ticket ticket3 = new Ticket("Санкт-Петербург", "Тюмень", 5_000, 15_00, 18_30);//3,5 часа
        Ticket ticket4 = new Ticket("Екатеринбург", "Москва", 3_500, 13_00, 15_30);//2,5 часа
        Ticket ticket5 = new Ticket("Тюмень", "Калининград", 8_000, 10_00, 16_30);//6,5 часов

        Ticket[] tickets = {ticket1, ticket2, ticket3, ticket4, ticket5};
        TicketTimeComparator comparator = new TicketTimeComparator();
        Arrays.sort(tickets, comparator);

        Assertions.assertArrayEquals(new Ticket[]{ticket2, ticket4, ticket1, ticket3, ticket5}, tickets);
    }

    @Test
    public void logicComparatorTest() {
        Ticket ticket1 = new Ticket("Москва", "Тюмень", 4_000, 12_00, 14_00);
        Ticket ticket2 = new Ticket("Москва", "Тюмень", 3_000, 15_00, 16_00);
        Ticket ticket3 = new Ticket("Москва", "Тюмень", 5_000, 11_00, 14_00);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Comparator<Ticket> ticketComparator = new TicketTimeComparator();
        Ticket[] searchResult = manager.searchAndSortBy("Москва", "Тюмень", ticketComparator);

        Assertions.assertArrayEquals(new Ticket[]{ticket2, ticket1, ticket3}, searchResult);
    }

    @Test
    public void shouldSearchThreeTickets() {
        Ticket ticket1 = new Ticket("Тюмень", "Москва", 6_000, 19_00, 22_00);
        Ticket ticket2 = new Ticket("Москва", "Санкт-Петербург", 3_000, 12_00, 13_00);
        Ticket ticket3 = new Ticket("Тюмень", "Москва", 4_000, 15_00, 17_00);
        Ticket ticket4 = new Ticket("Тюмень", "Москва", 5_000, 8_00, 10_00);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] searchResult = manager.search("Тюмень", "Москва");
        Assertions.assertArrayEquals(new Ticket[]{ticket3, ticket4, ticket1}, searchResult);
    }

    @Test
    public void shouldSearchOneTicket() {
        Ticket ticket1 = new Ticket("Тюмень", "Москва", 6_000, 19_00, 22_00);
        Ticket ticket2 = new Ticket("Москва", "Санкт-Петербург", 3_000, 12_00, 13_00);
        Ticket ticket3 = new Ticket("Тюмень", "Саратов", 4_000, 15_00, 17_00);
        Ticket ticket4 = new Ticket("Тюмень", "Нижний Тагил", 5_000, 8_00, 10_00);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] searchResult = manager.search("Тюмень", "Москва");
        Assertions.assertArrayEquals(new Ticket[]{ticket1}, searchResult);
    }

    @Test
    public void shouldSearchZeroTickets() {
        Ticket ticket1 = new Ticket("Тюмень", "Екатеринбург", 6_000, 19_00, 22_00);
        Ticket ticket2 = new Ticket("Москва", "Санкт-Петербург", 3_000, 12_00, 13_00);
        Ticket ticket3 = new Ticket("Тюмень", "Саратов", 4_000, 15_00, 17_00);
        Ticket ticket4 = new Ticket("Тюмень", "Нижний Тагил", 5_000, 8_00, 10_00);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] searchResult = manager.search("Тюмень", "Москва");
        Assertions.assertArrayEquals(new Ticket[]{}, searchResult);
    }
}
