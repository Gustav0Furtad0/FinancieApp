package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.openjfx.ArchiveRW.JavaxTeste;
import org.openjfx.back.User;
import org.openjfx.back.acao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {

    private int index = 0;

    private Boolean buscaActive = false;

    private List<acao> acoesList;

    private User pessoa;


    @FXML   
    private Button buscarBt;
    @FXML
    private TextField buscaField;
    public void busca() throws Exception {
        try {
            if (buscaField.getText() != null && buscaField.getText().length() > 0) {
                tabela.getItems().clear();
                tabela.setItems(montaTabelaBusca(buscaField.getText().toLowerCase()));
                buscaActive = true;
                paginationLabel.setText("0");
                paginationAnt.setDisable(true);
                paginationProx.setDisable(true);
            } else
                if (buscaActive) {
                    index = 0;
                    setItensTabela();
                    buscaActive = false;
                    paginationAnt.setDisable(false);
                    paginationProx.setDisable(false);
                }
                
        } catch (Exception e) {}
    }

    private ObservableList<AcaoTab> montaTabelaBusca(String arg) {
        ObservableList<AcaoTab> acoes = null;
        acoes = FXCollections.observableArrayList();
        for (acao acaoIn : acoesList) {
            if (acaoIn.getEmpresa().toLowerCase().contains(arg)) {
                AcaoTab acaoT = new AcaoTab(
                    acaoIn,
                    acaoIn.getEmpresa(),
                    acaoIn.getNome(),
                    acao.FormatD(acaoIn.getValor()),
                    acaoIn.getVolume().toString(),
                    acao.FormatD(Double.parseDouble(acaoIn.getVariacao().toString()))
                );
                acoes.add(acaoT);
            }
        }
        return acoes;
    }

    @FXML
    private Button paginationAnt;
    @FXML
    private Button paginationProx;
    @FXML
    private Label paginationLabel;
    public void paginationAntChanged(){
        if (index > 0) {
            index -= 1;
            paginationLabel.setText(Integer.toString(index+1));
            setItensTabela();
        }
    }

    public void paginationProxChanged(){
        if (!((index*10)+10 >= acoesList.size())) {
            index += 1;
            paginationLabel.setText(Integer.toString(index+1));
            setItensTabela();
        }
    }

    @FXML
    private TableView<AcaoTab> tabela;
    @FXML
    private TableColumn<AcaoTab, String> empresacol;
    @FXML
    private TableColumn<AcaoTab, String> stockcol;
    @FXML
    private TableColumn<AcaoTab, String> precocol;
    @FXML
    private TableColumn<AcaoTab, String> varcol;
    @FXML
    private TableColumn<AcaoTab, String> volcol;

    public void userRead() {
        Boolean arquivo = JavaxTeste.existe();
        
        if (arquivo) {
            try {
                pessoa = JavaxTeste.readFileUser();
            } catch (Exception e) { }
        } else {
            Optional<String> result;
            do {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Cadastro");
                dialog.setHeaderText("Usuário");

                result = dialog.showAndWait();
                if (result.isPresent()) {
                    pessoa = new User(result.get(), 5000);
                    try {
                        JavaxTeste.createFileUser(pessoa.userToMap());
                    } catch (IOException e) { }
                }
            } while(result.get().length() <= 3);
        }
    }

    @FXML
    private Label labelWel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userRead();

        labelWel.setText("Bem vindo, " + pessoa.getUsername() + "!");        

        try {
            this.acoesList = acao.puxaValores();
            pessoa.updateCarteira(acoesList);
        } catch (IOException e) {
            return;
        }
        
        empresacol.setCellValueFactory(
                new PropertyValueFactory<>("empresa"));
        stockcol.setCellValueFactory(
                new PropertyValueFactory<>("stock"));
        precocol.setCellValueFactory(
                new PropertyValueFactory<>("valor"));
        varcol.setCellValueFactory(
                new PropertyValueFactory<>("variacao"));
        volcol.setCellValueFactory(
            new PropertyValueFactory<>("volume"));
        setItensTabela();

        tabela.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                activeStock();
            }
        });

        stockBt.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                compraAcao();
            }
        });
    }

    private void setItensTabela(){
        tabela.getItems().clear(); 
        tabela.setItems(listaDeAcoes());
    }

    private ObservableList<AcaoTab> listaDeAcoes() {
        ObservableList<AcaoTab> acoes = null;
        acoes = FXCollections.observableArrayList();
        int limiter = (index*10)+10;
        for (int ler = index*10; ler < limiter;  ler++) {
            acao acaoIn = acoesList.get(ler);

            AcaoTab acaoT = new AcaoTab(
                acaoIn,
                acaoIn.getEmpresa(),
                acaoIn.getNome(),
                acao.FormatD(acaoIn.getValor()),
                acaoIn.getVolume().toString(),
                (acaoIn.getVariacao().equals(null) ? "0%" : acao.FormatD(Double.parseDouble(acaoIn.getVariacao().toString()))+"%")
                
            );
            acoes.add(acaoT);
        }
        return acoes;
    }

    @FXML
    private VBox stockArea;
    @FXML
    private Label stockLabel;
    @FXML
    private TextField stockField;
    @FXML
    private Button stockBt;
    public void activeStock() {
        System.out.println("selectedStock");
        if (tabela.getSelectionModel().getSelectedItem() == null) return;
        stockArea.setDisable(false);
        AcaoTab acao = tabela.getSelectionModel().getSelectedItem();
        stockLabel.setText(acao.getStock());
    }

    public void compraAcao() {
        AcaoTab acao = tabela.getSelectionModel().getSelectedItem();
        stockLabel.setText(acao.getStock());
        Alert alert = new Alert(AlertType.CONFIRMATION, "Comprar " + acao.getStock() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        int qtd;
        try {
            qtd = Integer.parseInt(stockField.getText());
        } catch (NumberFormatException e) {
            Alert warning = new Alert(AlertType.WARNING, "Quantidade inválida!!", ButtonType.YES);
            warning.showAndWait();
            return;
        }
        if (alert.getResult() == ButtonType.YES) {
            pessoa.compraAcao(
                acao.getAponta(), 
                LocalDate.now(), 
                qtd
            );
            try {
                JavaxTeste.updateFileUser(pessoa);
            } catch (IOException e) {}
        }
    }
    //@FXML
    //private Button carteiraBt;
    public void activeCarteira() throws IOException {
        App.setRoot("carteira");;
    }
}
