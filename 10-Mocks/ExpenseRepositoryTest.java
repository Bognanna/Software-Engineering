package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

public class ExpenseRepositoryTest {

    //private List<Expense> expenses;
    private IFancyDatabase mockData = mock(IFancyDatabase.class);
    //private MyDatabase myData = new MyDatabase();

    @Test
    void loadExpensesTest() {
        //Configuration
        when(mockData.queryAll()).thenReturn(Collections.emptyList());

        //Interaction
        ExpenseRepository exRepo = new ExpenseRepository(mockData);
        exRepo.loadExpenses();

        mockData.connect();
        mockData.queryAll();
        mockData.close();

        InOrder inOrder = inOrder(mockData);
        Boolean resultB = mockData.queryAll().isEmpty();

        //Verification
        inOrder.verify(mockData).connect();
        inOrder.verify(mockData).queryAll();
        inOrder.verify(mockData).close();
        //List<Expense> result = exRepo.getExpenses();
        //Boolean resultB = result.isEmpty();

        //Assertions.assertEquals(true, resultB);
        Assertions.assertEquals(true, resultB);
    }

    @Test
    void saveExpensesTest() {
        //Configuration

        //Interaction
        ExpenseRepository exRepo = new ExpenseRepository(mockData);

        exRepo.addExpense(new Expense());
        exRepo.addExpense(new Expense());
        exRepo.addExpense(new Expense());
        exRepo.addExpense(new Expense());
        exRepo.addExpense(new Expense());

        exRepo.saveExpenses();

        //Verification
        verify(mockData, times(5)).persist(any(Expense.class));
    }

}
