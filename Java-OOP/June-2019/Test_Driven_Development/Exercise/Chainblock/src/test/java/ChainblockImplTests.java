import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

public class ChainblockImplTests {
    private Chainblock chainblock;

    @Before
    public void initValues() {
        this.chainblock = new ChainblockImpl();
    }

    @Test
    public void addTransactionWithCheckContainsIt() {
        List<Transaction> transactions = this.createTransactions(3);
        transactions.forEach(t -> this.chainblock.add(t));
        Transaction transaction = transactions.get(1);

        Assert.assertTrue(this.chainblock.contains(transaction));
    }

    @Test
    public void containsWithNonExistTransaction() {
        List<Transaction> transactions = this.createTransactions(3);
        Transaction transaction = transactions.get(1);

        Assert.assertFalse(this.chainblock.contains(transaction));
    }

    @Test
    public void checkCountAfterAddTransactions() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.forEach(t -> this.chainblock.add(t));

        Assert.assertEquals(transactions.size(), this.chainblock.getCount());
    }

    @Test
    public void checkCountWithEmptyCollection() {
        Assert.assertEquals(0, this.chainblock.getCount());
    }

    @Test
    public void containsIdWithExistTransaction() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.forEach(t -> this.chainblock.add(t));
        Transaction transaction = transactions.get(0);

        Assert.assertTrue(this.chainblock.contains(transaction.getId()));
    }

    @Test
    public void containsIdWithNonExistTransaction() {
        Transaction transaction = this.createTransactions(1).get(0);
        Assert.assertFalse(this.chainblock.contains(transaction.getId()));
    }

    @Test
    public void changeTransactionStatusWithValidId() {
        List<Transaction> transactions = this.createTransactions(5);
        transactions.forEach(t -> this.chainblock.add(t));
        Transaction transaction = transactions.get(4);
        this.chainblock.changeTransactionStatus(transaction.getId(), TransactionStatus.SUCCESSFUL);
        Assert.assertEquals(transaction.getStatus(), TransactionStatus.SUCCESSFUL);

        this.chainblock.changeTransactionStatus(transaction.getId(), TransactionStatus.FAILED);
        Assert.assertEquals(transaction.getStatus(), TransactionStatus.FAILED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeTransactionStatusWithNotValidId() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.forEach(t -> this.chainblock.add(t));
        this.chainblock.changeTransactionStatus(5, TransactionStatus.FAILED);
    }

    @Test
    public void removeTransactionByIdWithValidId() {
        List<Transaction> transactions = this.createTransactions(5);
        transactions.forEach(t -> this.chainblock.add(t));
        Transaction transaction = transactions.get(4);

        this.chainblock.removeTransactionById(transaction.getId());
        Assert.assertFalse(this.chainblock.contains(transaction.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeTransactionByIdWithNotValidId() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.forEach(t -> this.chainblock.add(t));
        this.chainblock.removeTransactionById(5);
    }

    @Test
    public void getByIdWithValidId() {
        List<Transaction> transactions = this.createTransactions(5);
        transactions.forEach(t -> this.chainblock.add(t));
        Transaction transaction = transactions.get(4);

        Assert.assertEquals(transaction, this.chainblock.getById(transaction.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByIdWithNotValidId() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.forEach(t -> this.chainblock.add(t));
        this.chainblock.getById(5);
    }

    @Test
    public void getByTransactionStatusWithExistStatus() {
        List<Transaction> transactions = this.createTransactions(3);
        transactions.get(0).setStatus(TransactionStatus.ABORTED);
        transactions.get(0).setAmount(12.3);

        transactions.get(1).setStatus(TransactionStatus.FAILED);
        transactions.get(1).setAmount(13);

        transactions.get(2).setStatus(TransactionStatus.ABORTED);
        transactions.get(2).setAmount(14);

        Transaction[] actual = new Transaction[]{
                transactions.get(2),
                transactions.get(0)
        };

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<Transaction> iterableTransactions = this.chainblock.getByTransactionStatus(TransactionStatus.ABORTED);
        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByTransactionStatusWithNonExistStatus() {
        List<Transaction> transactions = this.createTransactions(3);
        transactions.get(0).setStatus(TransactionStatus.ABORTED);
        transactions.get(1).setStatus(TransactionStatus.FAILED);
        transactions.get(2).setStatus(TransactionStatus.SUCCESSFUL);

        transactions.forEach(t -> this.chainblock.add(t));
        this.chainblock.getByTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void getAllSendersWithTransactionStatusWithExistStatus() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setStatus(TransactionStatus.SUCCESSFUL);
        transactions.get(0).setAmount(5);
        transactions.get(0).setFrom("svetlio");

        transactions.get(1).setStatus(TransactionStatus.SUCCESSFUL);
        transactions.get(1).setAmount(6);
        transactions.get(1).setFrom("tosho");

        transactions.get(2).setStatus(TransactionStatus.ABORTED);
        transactions.get(2).setAmount(12);
        transactions.get(2).setFrom("svetlio");

        transactions.get(3).setStatus(TransactionStatus.SUCCESSFUL);
        transactions.get(3).setAmount(2);
        transactions.get(3).setFrom("mihail");

        String[] actual = new String[]{
                "tosho",
                "svetlio",
                "mihail"
        };

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<String> iterableSenders = this.chainblock
                .getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);

        String[] expected = StreamSupport
                .stream(iterableSenders.spliterator(), false)
                .toArray(String[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllSendersWithTransactionStatusWithNonExistStatus() {
        List<Transaction> transactions = this.createTransactions(3);
        transactions.get(0).setStatus(TransactionStatus.ABORTED);
        transactions.get(1).setStatus(TransactionStatus.FAILED);
        transactions.get(2).setStatus(TransactionStatus.SUCCESSFUL);

        transactions.forEach(t -> this.chainblock.add(t));
        this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void getAllReceiversWithTransactionStatusWithExistStatus() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setStatus(TransactionStatus.SUCCESSFUL);
        transactions.get(0).setAmount(5);
        transactions.get(0).setTo("svetlio");

        transactions.get(1).setStatus(TransactionStatus.SUCCESSFUL);
        transactions.get(1).setAmount(6);
        transactions.get(1).setTo("tosho");

        transactions.get(2).setStatus(TransactionStatus.ABORTED);
        transactions.get(2).setAmount(12);
        transactions.get(2).setTo("svetlio");

        transactions.get(3).setStatus(TransactionStatus.SUCCESSFUL);
        transactions.get(3).setAmount(2);
        transactions.get(3).setTo("mihail");

        String[] actual = new String[]{
                "tosho",
                "svetlio",
                "mihail"
        };

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<String> iterableReceivers = this.chainblock
                .getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);

        String[] expected = StreamSupport
                .stream(iterableReceivers.spliterator(), false)
                .toArray(String[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllReceiversWithTransactionStatusStatusWithNonExistStatus() {
        List<Transaction> transactions = this.createTransactions(3);
        transactions.get(0).setStatus(TransactionStatus.ABORTED);
        transactions.get(1).setStatus(TransactionStatus.FAILED);
        transactions.get(2).setStatus(TransactionStatus.SUCCESSFUL);

        transactions.forEach(t -> this.chainblock.add(t));
        this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void getAllOrderedByAmountDescendingThenByIdWithValidCollection() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setAmount(5);
        transactions.get(1).setAmount(6);
        transactions.get(2).setAmount(5);
        transactions.get(3).setAmount(2);

        Transaction[] actual = new Transaction[]{
                transactions.get(1),
                transactions.get(0),
                transactions.get(2),
                transactions.get(3),
        };

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<Transaction> iterableTransactions = this.chainblock.getAllOrderedByAmountDescendingThenById();

        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void getAllOrderedByAmountDescendingThenByIdWithEmptyCollection() {
        Transaction[] actual = new Transaction[0];
        Iterable<Transaction> iterableTransactions = this.chainblock.getAllOrderedByAmountDescendingThenById();

        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void getBySenderOrderedByAmountDescendingWithExistSender() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setAmount(1);
        transactions.get(0).setFrom("svetlio");

        transactions.get(1).setAmount(33);
        transactions.get(1).setFrom("tosho");

        transactions.get(2).setAmount(12);
        transactions.get(2).setFrom("svetlio");

        transactions.get(3).setAmount(2);
        transactions.get(3).setFrom("svetlio");

        Transaction[] actual = new Transaction[]{
                transactions.get(2),
                transactions.get(3),
                transactions.get(0),
        };

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<Transaction> iterableSenders = this.chainblock
                .getBySenderOrderedByAmountDescending("svetlio");

        Transaction[] expected = StreamSupport
                .stream(iterableSenders.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBySenderOrderedByAmountDescendingWithNonExistSender() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setFrom("svetlio");
        transactions.get(1).setFrom("tosho");
        transactions.get(2).setFrom("gosho");
        transactions.get(3).setFrom("svetlio");

        transactions.forEach(t -> this.chainblock.add(t));
        this.chainblock.getBySenderOrderedByAmountDescending("misho");
    }

    @Test
    public void getByReceiverOrderedByAmountThenByIdWithExistReceiver() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setAmount(1);
        transactions.get(0).setTo("svetlio");

        transactions.get(1).setAmount(33);
        transactions.get(1).setTo("tosho");

        transactions.get(2).setAmount(12);
        transactions.get(2).setTo("svetlio");

        transactions.get(3).setAmount(2);
        transactions.get(3).setTo("svetlio");

        Transaction[] actual = new Transaction[]{
                transactions.get(2),
                transactions.get(3),
                transactions.get(0),
        };

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<Transaction> iterableReceivers = this.chainblock
                .getByReceiverOrderedByAmountThenById("svetlio");

        Transaction[] expected = StreamSupport
                .stream(iterableReceivers.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByReceiverOrderedByAmountThenByIdWithNonExistReceiver() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setTo("svetlio");
        transactions.get(1).setTo("tosho");
        transactions.get(2).setTo("gosho");
        transactions.get(3).setTo("svetlio");

        transactions.forEach(t -> this.chainblock.add(t));
        this.chainblock.getByReceiverOrderedByAmountThenById("misho");
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountWithFullCollection() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setAmount(1);
        transactions.get(0).setStatus(TransactionStatus.SUCCESSFUL);

        transactions.get(1).setAmount(33);
        transactions.get(1).setStatus(TransactionStatus.SUCCESSFUL);

        transactions.get(2).setAmount(12);
        transactions.get(2).setStatus(TransactionStatus.UNAUTHORIZED);

        transactions.get(3).setAmount(5);
        transactions.get(3).setStatus(TransactionStatus.SUCCESSFUL);

        Transaction[] actual = new Transaction[]{
                transactions.get(3),
                transactions.get(0),
        };

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<Transaction> iterableTransactions = this.chainblock
                .getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 5);

        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountWithEmptyCollection() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setAmount(1);
        transactions.get(0).setStatus(TransactionStatus.SUCCESSFUL);

        transactions.get(1).setAmount(33);
        transactions.get(1).setStatus(TransactionStatus.SUCCESSFUL);

        transactions.get(2).setAmount(12);
        transactions.get(2).setStatus(TransactionStatus.UNAUTHORIZED);

        transactions.get(3).setAmount(5);
        transactions.get(3).setStatus(TransactionStatus.SUCCESSFUL);

        Transaction[] actual = new Transaction[0];

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<Transaction> iterableTransactions = this.chainblock
                .getByTransactionStatusAndMaximumAmount(TransactionStatus.UNAUTHORIZED, 11);

        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void getBySenderAndMinimumAmountDescendingWithFullCollection() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setAmount(2);
        transactions.get(0).setFrom("tomi");

        transactions.get(1).setAmount(7);
        transactions.get(1).setFrom("tomi");

        transactions.get(2).setAmount(12);
        transactions.get(2).setFrom("poni");

        transactions.get(3).setAmount(33);
        transactions.get(3).setFrom("tomi");

        Transaction[] actual = new Transaction[]{
                transactions.get(3),
                transactions.get(1)
        };

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<Transaction> iterableTransactions = this.chainblock
                .getBySenderAndMinimumAmountDescending("tomi", 6);

        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBySenderAndMinimumAmountDescendingWithNonExistReceiver() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setAmount(2);
        transactions.get(0).setFrom("tomi");

        transactions.get(1).setAmount(33);
        transactions.get(1).setFrom("tomi");

        transactions.get(2).setAmount(12);
        transactions.get(2).setFrom("poni");

        transactions.get(3).setAmount(7);
        transactions.get(3).setFrom("tomi");

        transactions.forEach(t -> this.chainblock.add(t));
        this.chainblock.getBySenderAndMinimumAmountDescending("tomi", 33);
    }

    @Test
    public void getByReceiverAndAmountRangeWithFullCollection() {
        List<Transaction> transactions = this.createTransactions(5);
        transactions.get(0).setAmount(2);
        transactions.get(0).setTo("tomi");

        transactions.get(1).setAmount(7);
        transactions.get(1).setTo("tomi");

        transactions.get(2).setAmount(12);
        transactions.get(2).setTo("poni");

        transactions.get(3).setAmount(33);
        transactions.get(3).setTo("tomi");

        transactions.get(4).setAmount(7);
        transactions.get(4).setTo("tomi");

        Transaction[] actual = new Transaction[]{
                transactions.get(1),
                transactions.get(4),
                transactions.get(0)
        };

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<Transaction> iterableTransactions = this.chainblock
                .getByReceiverAndAmountRange("tomi", 2, 33);

        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByReceiverAndAmountRangeWithEmptyCollection() {
        List<Transaction> transactions = this.createTransactions(4);
        transactions.get(0).setAmount(2);
        transactions.get(0).setTo("tomi");

        transactions.get(1).setAmount(7);
        transactions.get(1).setTo("tomi");

        transactions.get(2).setAmount(12);
        transactions.get(2).setTo("poni");

        transactions.get(3).setAmount(33);
        transactions.get(3).setTo("tomi");

        transactions.forEach(t -> this.chainblock.add(t));
        this.chainblock.getByReceiverAndAmountRange("tomi", 1, 2);
    }

    @Test
    public void getAllInAmountRangeWithFullCollection() {
        List<Transaction> transactions = this.createTransactions(5);
        transactions.get(0).setAmount(2);
        transactions.get(1).setAmount(7);
        transactions.get(2).setAmount(12);
        transactions.get(3).setAmount(33);
        transactions.get(4).setAmount(7);

        Transaction[] actual = new Transaction[]{
                transactions.get(0),
                transactions.get(1),
                transactions.get(2),
                transactions.get(4)
        };

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<Transaction> iterableTransactions = this.chainblock
                .getAllInAmountRange(2, 12);

        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void getAllInAmountRangeWithEmptyCollection() {
        List<Transaction> transactions = this.createTransactions(5);
        transactions.get(0).setAmount(2);
        transactions.get(1).setAmount(7);
        transactions.get(2).setAmount(12);
        transactions.get(3).setAmount(33);
        transactions.get(4).setAmount(7);

        Transaction[] actual = new Transaction[0];

        transactions.forEach(t -> this.chainblock.add(t));
        Iterable<Transaction> iterableTransactions = this.chainblock
                .getAllInAmountRange(34, 55);

        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void testIteratorWithFullCollection() {
        List<Transaction> transactions = this.createTransactions(5);
        Transaction[] actual = transactions.toArray(new Transaction[0]);

        transactions.forEach(t -> this.chainblock.add(t));
        Iterator<Transaction> iteratorTransactions = this.chainblock.iterator();
        Iterable<Transaction> iterableTransactions = () -> iteratorTransactions;

        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void testIteratorWithEmptyCollection() {
        Transaction[] actual = new Transaction[0];

        Iterator<Transaction> iteratorTransactions = this.chainblock.iterator();
        Iterable<Transaction> iterableTransactions = () -> iteratorTransactions;

        Transaction[] expected = StreamSupport
                .stream(iterableTransactions.spliterator(), false)
                .toArray(Transaction[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    private List<Transaction> createTransactions(int count) {
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            Transaction transaction = new TransactionImpl(i,
                    TransactionStatus.values()[(i - 1) % 4],
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    i * 5.3);
            transactions.add(transaction);
        }

        return transactions;
    }
}
