package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {

    private ExpenseRepository mockResp = mock(ExpenseRepository.class);

    @Test
    public void calculateTotalTest(){
        //Configuration
        ExpenseManager expMena = new ExpenseManager(mockResp, new FancyService());
        List<Expense> expenses = new ArrayList<Expense>();
        Expense expense = new Expense();

        when(mockResp.getExpenses()).thenReturn(expenses);

        //Interaction
        expense.setAmount(10);
        for(int i = 0; i<3; ++i){
            expenses.add(expense);
        }

        //Verification
        Assertions.assertEquals(30, expMena.calculateTotal());
    }

    @Test
    public void calculateTotalForCategoryTest(){
        //Configuration
        ExpenseManager expMena = new ExpenseManager(mockResp, new FancyService());
        List<Expense> car_expenses = new ArrayList<Expense>();
        List<Expense> home_expenses = new ArrayList<Expense>();
        Expense car_expense = new Expense();
        Expense home_expense = new Expense();

        when(mockResp.getExpensesByCategory(anyString())).thenReturn(new ArrayList<Expense>());
        when(mockResp.getExpensesByCategory("Car")).thenReturn(car_expenses);
        when(mockResp.getExpensesByCategory("Home")).thenReturn(home_expenses);

        //Interaction
        car_expense.setAmount(10);
        car_expense.setCategory("Car");
        for(int i = 0; i<3; ++i){
            car_expenses.add(car_expense);
        }

        home_expense.setAmount(100);
        home_expense.setCategory("Home");
        for(int i = 0; i<2; ++i){
            home_expenses.add(home_expense);
        }

        //Verification
        Assertions.assertEquals(expMena.calculateTotalForCategory("Car"), 30);
        Assertions.assertEquals(expMena.calculateTotalForCategory("Home"), 200);
        Assertions.assertEquals(expMena.calculateTotalForCategory("Food"), 0);
        Assertions.assertEquals(expMena.calculateTotalForCategory("Sport"),0);
    }

    @Test
    public void calculateTotalInDollarsTest() throws ConnectException {
        //Configuration
        FancyService mockServ = mock(FancyService.class);
        ExpenseManager expMena = new ExpenseManager(mockResp, mockServ);
        List<Expense> expenses = new ArrayList<Expense>();
        Expense expense = new Expense();

        when(mockResp.getExpenses()).thenReturn(expenses);
        when(mockServ.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                        Object[] args = invocationOnMock.getArguments();
                        Object inOnMock = invocationOnMock.getMock();
                        return ((double) args[0])/4.0;
                    }
                }
        );

        //Interaction
        expense.setAmount(4);
        for(int i=0; i<5; ++i){
            expenses.add(expense);
        }

        //Verification
        //Assertions.assertEquals(mockServ.convert((double)expMena.calculateTotal(),"PLN", "USD"),5);
        Assertions.assertEquals(expMena.calculateTotalInDollars(), 5);
    }
}
