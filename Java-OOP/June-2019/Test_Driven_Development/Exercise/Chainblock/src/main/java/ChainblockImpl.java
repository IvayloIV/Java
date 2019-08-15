import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock{
    private Map<Integer, Transaction> transactionMap;
    private List<Transaction> transactionList;

    public ChainblockImpl() {
        this.transactionMap = new HashMap<>();
        this.transactionList = new ArrayList<>();
    }

    public int getCount() {
        return this.transactionList.size();
    }

    public void add(Transaction transaction) {
        this.transactionMap.put(transaction.getId(), transaction);
        this.transactionList.add(transaction);
    }

    public boolean contains(Transaction transaction) {
        return this.contains(transaction.getId());
    }

    public boolean contains(int id) {
        return this.transactionMap.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException();
        }
        this.transactionMap.get(id).setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException();
        }
        Transaction transaction = this.transactionMap.remove(id);
        this.transactionList.remove(transaction);
    }

    public Transaction getById(int id) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException();
        }
        return this.transactionMap.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = this.transactionList.stream()
                .filter(t -> t.getStatus().name().equals(status.name()))
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .collect(Collectors.toList());

        if (transactions.size() == 0) {
            throw new IllegalArgumentException();
        }

        return transactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<String> senders = this.transactionList.stream()
                .filter(t -> t.getStatus().name().equals(status.name()))
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .map(Transaction::getFrom)
                .collect(Collectors.toList());

        if (senders.size() == 0) {
            throw new IllegalArgumentException();
        }

        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> senders = this.transactionList.stream()
                .filter(t -> t.getStatus().name().equals(status.name()))
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .map(Transaction::getTo)
                .collect(Collectors.toList());

        if (senders.size() == 0) {
            throw new IllegalArgumentException();
        }

        return senders;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.transactionList.stream()
                .sorted((t1, t2) -> {
                    if (t1.getAmount() != t2.getAmount()) {
                        return Double.compare(t2.getAmount(), t1.getAmount());
                    }
                    return t1.getId() - t2.getId();
                })
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> senders = this.transactionList.stream()
                .filter(s -> s.getFrom().equals(sender))
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .collect(Collectors.toList());

        if (senders.size() == 0) {
            throw new IllegalArgumentException();
        }

        return senders;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> receivers = this.transactionList.stream()
                .filter(s -> s.getTo().equals(receiver))
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .collect(Collectors.toList());

        if (receivers.size() == 0) {
            throw new IllegalArgumentException();
        }

        return receivers;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return this.transactionList.stream()
                .filter(t -> t.getStatus().name().equals(status.name()) && t.getAmount() <= amount)
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> transactions = this.transactionList.stream()
                .filter(t -> t.getFrom().equals(sender) && t.getAmount() > amount)
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .collect(Collectors.toList());

        if (transactions.size() == 0) {
            throw new IllegalArgumentException();
        }
        return transactions;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> transactions = this.transactionList.stream()
                .filter(t -> t.getTo().equals(receiver) && t.getAmount() >= lo && t.getAmount() < hi)
                .sorted((t1, t2) -> {
                    if (t1.getAmount() != t2.getAmount()) {
                        return Double.compare(t2.getAmount(), t1.getAmount());
                    }
                    return t1.getId() - t2.getId();
                })
                .collect(Collectors.toList());

        if (transactions.size() == 0) {
            throw new IllegalArgumentException();
        }
        return transactions;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return this.transactionList.stream()
                .filter(t -> t.getAmount() >= lo && t.getAmount() <= hi)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return this.transactionList.iterator();
    }
}
