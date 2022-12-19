package ar.com.bress.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import ar.com.bress.currency.CurrencyConverter;
import ar.com.bress.currency.Symbol;
import ar.com.bress.temperature.TerperatureConverter;

public class WindowConverter extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtAmountCurrency;
	private JTextField txtAmountConverted;
	private JTextField txtChange;
	private JButton btnConverter;
	private JComboBox cbxFromCurrency;
	private JComboBox cbxToCurrency;	
	private JTextField txtTemperatureOne;
	private JTextField txtTemperatureTwo;

	
	private JComboBox cbxTemperatureOne;
	private JComboBox cbxTemperatureTwo;
	private boolean indexChangeOne = false;
	private boolean indexChangeTwo = false;

	TerperatureConverter terperatureConverter = new TerperatureConverter();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowConverter frame = new WindowConverter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public WindowConverter() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initializeComponents();
		
		setTitle("CONVERSOR");
		setResizable(false);
		setLocationRelativeTo(null);
				
	}


	/**
	 * Initialize components.
	 */
	private void initializeComponents() {


		// Currency Converter
		
		CurrencyConverter currencyConverter = new CurrencyConverter();
		
		Symbol [] symbols = currencyConverter.getSymbol();

		
		setBounds(100, 100, 543, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 11, 525, 268);
		contentPane.add(tabbedPane);
		
		JPanel currencyPanel = new JPanel();
		tabbedPane.addTab("Divisas", null, currencyPanel, null);
		currencyPanel.setLayout(null);
		
		JLabel lblFromCurrency = new JLabel("De divisa");
		lblFromCurrency.setBounds(13, 22, 66, 14);
		currencyPanel.add(lblFromCurrency);		
        
		cbxFromCurrency = new JComboBox(symbols);				
		cbxFromCurrency.setBounds(81, 18, 295, 22);
		cbxFromCurrency.addActionListener(this);
		currencyPanel.add(cbxFromCurrency);
		
		JLabel lblToCurrency = new JLabel("A divisa");
		lblToCurrency.setBounds(13, 82, 46, 14);
		currencyPanel.add(lblToCurrency);
		
		cbxToCurrency = new JComboBox(symbols);		
		cbxToCurrency.setBounds(81, 78, 295, 22);
		cbxToCurrency.addActionListener(this);
		currencyPanel.add(cbxToCurrency);		
		
		JLabel lblAmountCurrency = new JLabel("Monto");
		lblAmountCurrency.setBounds(13, 139, 46, 14);
		currencyPanel.add(lblAmountCurrency);
		
		txtAmountCurrency = new JTextField();
		txtAmountCurrency.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAmountCurrency.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
		    	activeConverter();
			}
		});
		txtAmountCurrency.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				int key = e.getKeyChar();
			    boolean numbers = key >= 48 && key <= 57 || key == 46;

				if (numbers)
			    {			    	
					clearDataCurrency();
					activeConverter();
			    } else {
			    	e.consume();
			    }
			}
		});
		txtAmountCurrency.setBounds(81, 136, 86, 20);
		txtAmountCurrency.setColumns(10);
		currencyPanel.add(txtAmountCurrency);
		
		btnConverter = new JButton("Convertir");
		btnConverter.setEnabled(false);
		btnConverter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConverter.setBounds(191, 194, 131, 34);
		btnConverter.addActionListener(this);
		currencyPanel.add(btnConverter);
		
		JLabel lblChange = new JLabel("Cambio");
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblChange.setBounds(424, 52, 86, 14);
		currencyPanel.add(lblChange);
		
		txtChange = new JTextField();
		txtChange.setHorizontalAlignment(SwingConstants.RIGHT);
		txtChange.setBackground(new Color(255, 255, 255));
		txtChange.setEditable(false);
		txtChange.setBounds(424, 73, 86, 20);
		txtChange.setColumns(10);
		currencyPanel.add(txtChange);
		
		JLabel lblAmountConverted = new JLabel("Conversi\u00F3n");
		lblAmountConverted.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmountConverted.setBounds(424, 108, 86, 14);
		currencyPanel.add(lblAmountConverted);
		
		txtAmountConverted = new JTextField();
		txtAmountConverted.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAmountConverted.setBackground(new Color(255, 255, 255));
		txtAmountConverted.setEditable(false);
		txtAmountConverted.setBounds(424, 133, 86, 20);
		txtAmountConverted.setColumns(10);
		currencyPanel.add(txtAmountConverted);
		


		// Temperature Converter
		
		JPanel temperaturePanel = new JPanel();
		tabbedPane.addTab("Temperatura", null, temperaturePanel, null);
		temperaturePanel.setLayout(null);
		
		txtTemperatureOne = new JTextField();
		txtTemperatureOne.setHorizontalAlignment(SwingConstants.CENTER);
		txtTemperatureOne.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				int key = e.getKeyChar();
			    boolean numbers = (key >= 48 && key <= 57) || key == 46 || key == 45 || key == 8;
			    
			    if (!numbers) {
			    	e.consume();					
				}			
			}

			@Override
			public void keyReleased(KeyEvent e) {

				int key = e.getKeyChar();

				boolean numbers = (key >= 48 && key <= 57) || 
						           key == 46 || 
						          (!txtTemperatureOne.getText().equals("-") && 
						        		  txtTemperatureOne.getText().length() > 0 && 
						        		  key == 8);
			    				
				if (numbers)
			    {	
					char tempFrom = cbxTemperatureOne.getSelectedItem().toString().charAt(0);
					char tempTo = cbxTemperatureTwo.getSelectedItem().toString().charAt(0);
					Double tempAmount =	Double.parseDouble(txtTemperatureOne.getText());	
					terperatureConverter.Converter(tempFrom, tempTo, tempAmount);
					txtTemperatureTwo.setText(String.format (Locale.US, "%.2f", terperatureConverter.getTemperatureConvert()));				

			    } else {
			    	if(txtTemperatureOne.getText().length() == 0 || txtTemperatureOne.getText().equals("-")) {
			    		txtTemperatureTwo.setText("");
			    	}
			    	e.consume();
			    }
			}
			
		});
		txtTemperatureOne.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtTemperatureOne.setBounds(109, 61, 125, 42);
		temperaturePanel.add(txtTemperatureOne);
		txtTemperatureOne.setColumns(10);
				
		cbxTemperatureOne = new JComboBox();		
		cbxTemperatureOne.setFont(new Font("Roboto", Font.PLAIN, 12));
		cbxTemperatureOne.setModel(new DefaultComboBoxModel(new String[] {"Celsius", "Fahrenheit", "Kelvin"}));
		cbxTemperatureOne.setBounds(109, 104, 125, 34);
		cbxTemperatureOne.addActionListener(this);
		temperaturePanel.add(cbxTemperatureOne);
				
		txtTemperatureTwo = new JTextField();
		txtTemperatureTwo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTemperatureTwo.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {

				int key = e.getKeyChar();
			    boolean numbers = (key >= 48 && key <= 57) || key == 46 || key == 45 || key == 8;
			    
			    if (!numbers) {
			    	e.consume();					
				}			
			}

			@Override
			public void keyReleased(KeyEvent e) {

				int key = e.getKeyChar();

				boolean numbers = (key >= 48 && key <= 57) || 
				                  key == 46 || 
				                  (!txtTemperatureTwo.getText().equals("-") && 
				                		  txtTemperatureTwo.getText().length() > 0 
				                		  && key == 8);
			    
				if (numbers)
			    {	
					char tempFrom = cbxTemperatureTwo.getSelectedItem().toString().charAt(0);
					char tempTo = cbxTemperatureOne.getSelectedItem().toString().charAt(0);
					Double tempAmount =	Double.parseDouble(txtTemperatureTwo.getText());	
					terperatureConverter.Converter(tempFrom, tempTo, tempAmount);
					txtTemperatureOne.setText(String.format (Locale.US, "%.2f", terperatureConverter.getTemperatureConvert()));				
			    } else {
			    	if(txtTemperatureTwo.getText().length() == 0 || txtTemperatureTwo.getText().equals("-")) {
			    		txtTemperatureOne.setText("");
			    	}
			    	e.consume();
			    }
			}	
		});
		txtTemperatureTwo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtTemperatureTwo.setColumns(10);
		txtTemperatureTwo.setBounds(289, 61, 125, 42);
		temperaturePanel.add(txtTemperatureTwo);
		
		cbxTemperatureTwo = new JComboBox();
		cbxTemperatureTwo.setFont(new Font("Roboto", Font.PLAIN, 12));
		cbxTemperatureTwo.setModel(new DefaultComboBoxModel(new String[] {"Celsius", "Fahrenheit", "Kelvin"}));
		cbxTemperatureTwo.setSelectedIndex(1);
		cbxTemperatureTwo.setBounds(289, 104, 125, 34);
		cbxTemperatureTwo.addActionListener(this);
		temperaturePanel.add(cbxTemperatureTwo);
		
		JLabel lblNewLabel = new JLabel("=");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(233, 63, 57, 40);
		temperaturePanel.add(lblNewLabel);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(btnConverter == e.getSource()) {			
					
			Symbol symbolObj;
			
			symbolObj = (Symbol) cbxFromCurrency.getSelectedItem();			
	        String symbolFrom = symbolObj.getSymbol();
	        
			symbolObj = (Symbol) cbxToCurrency.getSelectedItem();			
	        String symbolTo = symbolObj.getSymbol();
	        
	        Double amountCurrency = Double.parseDouble(txtAmountCurrency.getText()); 
			
			CurrencyConverter currencyConverter = new CurrencyConverter(symbolFrom, symbolTo, amountCurrency);

			txtChange.setText(String.format (Locale.US, "%.2f", currencyConverter.getCurrencyConvert() / amountCurrency));
			
			txtAmountConverted.setText(String.format (Locale.US, "%.2f", currencyConverter.getCurrencyConvert()));
	        
		}
		

		if(cbxToCurrency == e.getSource() || cbxFromCurrency == e.getSource()) {
			clearDataCurrency();
		}

		if(cbxTemperatureOne == e.getSource()) {
			
			if(cbxTemperatureOne.getSelectedItem() == cbxTemperatureTwo.getSelectedItem()) {			
				indexChangeOne = true;
				cbxTemperatureTwo.setSelectedIndex(cbxTemperatureOne.getSelectedIndex() == 0 ?1 :0);
			}
			
			if(indexChangeTwo) {	

				indexChangeTwo = false;

			} else {

				if(txtTemperatureOne.getText().length() != 0) {
					
					char tempFrom = cbxTemperatureOne.getSelectedItem().toString().charAt(0);
					char tempTo = cbxTemperatureTwo.getSelectedItem().toString().charAt(0);
					
					Double tempAmount =	Double.parseDouble(txtTemperatureOne.getText());	
					terperatureConverter.Converter(tempFrom, tempTo, tempAmount);
					txtTemperatureTwo.setText(String.format (Locale.US, "%.2f", terperatureConverter.getTemperatureConvert()));				
					
				} else if(txtTemperatureTwo.getText().length() != 0) {
					
					char tempFrom = cbxTemperatureTwo.getSelectedItem().toString().charAt(0);
					char tempTo = cbxTemperatureOne.getSelectedItem().toString().charAt(0);
					Double tempAmount =	Double.parseDouble(txtTemperatureTwo.getText());	
					terperatureConverter.Converter(tempFrom, tempTo, tempAmount);
					txtTemperatureOne.setText(String.format (Locale.US, "%.2f", terperatureConverter.getTemperatureConvert()));										
				}
			}						
		}


		if(cbxTemperatureTwo == e.getSource()) {
			
			if(cbxTemperatureOne.getSelectedItem() == cbxTemperatureTwo.getSelectedItem()) {			
				indexChangeTwo = true;
				cbxTemperatureOne.setSelectedIndex(cbxTemperatureTwo.getSelectedIndex() == 0 ?1 :0);
			}

			if(indexChangeOne) {
				indexChangeOne = false;				
			} else {

				if(txtTemperatureTwo.getText().length() != 0) {
					char tempFrom = cbxTemperatureTwo.getSelectedItem().toString().charAt(0);
					char tempTo = cbxTemperatureOne.getSelectedItem().toString().charAt(0);
					Double tempAmount =	Double.parseDouble(txtTemperatureTwo.getText());	
					terperatureConverter.Converter(tempFrom, tempTo, tempAmount);
					txtTemperatureOne.setText(String.format (Locale.US, "%.2f", terperatureConverter.getTemperatureConvert()));										
					
				} else if(txtTemperatureOne.getText().length() != 0) {
					char tempFrom = cbxTemperatureOne.getSelectedItem().toString().charAt(0);
					char tempTo = cbxTemperatureTwo.getSelectedItem().toString().charAt(0);
					Double tempAmount =	Double.parseDouble(txtTemperatureOne.getText());	
					terperatureConverter.Converter(tempFrom, tempTo, tempAmount);
					txtTemperatureTwo.setText(String.format (Locale.US, "%.2f", terperatureConverter.getTemperatureConvert()));				
				}				
			}
		}		
	}
	
	
	private void activeConverter() {

		if(txtAmountCurrency.getText().trim().length() != 0 &&  Double.parseDouble(txtAmountCurrency.getText()) != 0) {
			btnConverter.setEnabled(true);				
		} else {
			btnConverter.setEnabled(false);
		}		
	}


	private void clearDataCurrency() {
		txtChange.setText("");
		txtAmountConverted.setText("");		
	}
}
