package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.ArchiveRW.JavaxTeste;
import org.openjfx.back.AcaoComprada;
import org.openjfx.back.User;
import org.openjfx.front.AcaoCompTab;
import org.openjfx.back.AcaoListada;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
// import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CarteiraController implements Initializable {

    User pessoa;

    public void userRead() {
        try {
            pessoa = JavaxTeste.readFileUser();
        } catch (Exception e) { }
    }

    public void voltar() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private Label labelWel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        userRead();

        labelWel.setText(pessoa.getUsername());

        setInfo();

        empresacol.setCellValueFactory(
                new PropertyValueFactory<>("empresa"));
        stockcol.setCellValueFactory(
                new PropertyValueFactory<>("stock"));
        totalcol.setCellValueFactory(
                new PropertyValueFactory<>("valorTotal"));
        qtdcol.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
        precoCompracol.setCellValueFactory(
            new PropertyValueFactory<>("valorCompra"));
        precocol.setCellValueFactory(
            new PropertyValueFactory<>("valor"));
        ganhoscol.setCellValueFactory(
            new PropertyValueFactory<>("ganhos"));
        datacol.setCellValueFactory(
            new PropertyValueFactory<>("dataCompra"));
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
                vendeAcao();
            }
        });
    }

    @FXML
    private TableView<AcaoCompTab> tabela;
    @FXML
    private TableColumn<AcaoCompTab, String> empresacol;
    @FXML
    private TableColumn<AcaoCompTab, String> stockcol;
    @FXML
    private TableColumn<AcaoCompTab, String> totalcol;
    @FXML
    private TableColumn<AcaoCompTab, String> qtdcol;
    @FXML
    private TableColumn<AcaoCompTab, String> precocol;
    @FXML
    private TableColumn<AcaoCompTab, String> precoCompracol;
    @FXML
    private TableColumn<AcaoCompTab, String> ganhoscol;
    @FXML
    private TableColumn<AcaoCompTab, String> datacol;

    private void setItensTabela(){
        tabela.getItems().clear(); 
        tabela.setItems(listaDeAcoes());
    }

    private ObservableList<AcaoCompTab> listaDeAcoes() {
        ObservableList<AcaoCompTab> acoes = null;
        acoes = FXCollections.observableArrayList();
        for (AcaoComprada acaoIn : pessoa.acoesConta()) {
            AcaoCompTab acaoT = new AcaoCompTab(
                acaoIn,
                acaoIn.getEmpresa(),
                acaoIn.getNome(),
                AcaoListada.FormatD(acaoIn.getValor()),
                AcaoListada.FormatD(acaoIn.getValorCompra()),
                AcaoListada.FormatD(acaoIn.getValor() - acaoIn.getValorCompra()),
                acaoIn.getDataCompra().toString(),
                Integer.toString(acaoIn.getQuantidade()),
                AcaoListada.FormatD(acaoIn.getQuantidade() * acaoIn.getValor())
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
        AcaoCompTab acao = tabela.getSelectionModel().getSelectedItem();
        stockLabel.setText(acao.getStock());
    }

    public void vendeAcao() {
        AcaoCompTab acao = tabela.getSelectionModel().getSelectedItem();
        stockLabel.setText(acao.getStock());
        Alert alert = new Alert(AlertType.CONFIRMATION, "Comprar " + acao.getStock() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        int qtd;
        try {
            qtd = Integer.parseInt(stockField.getText());
            if (qtd > acao.getAponta().getQuantidade()) {
                Alert warning = new Alert(AlertType.WARNING, "Quantidade maior do que a possível!!", ButtonType.YES);
                warning.showAndWait();
                return;
            }
        } catch (Exception e) {
            Alert warning = new Alert(AlertType.WARNING, "Quantidade inválida!!", ButtonType.YES);
            warning.showAndWait();
            return;
        }

        if (alert.getResult() == ButtonType.YES) {
            pessoa.vendeAcao(
                acao.getAponta(),
                qtd
            );
            try {
                JavaxTeste.updateFileUser(pessoa);
            } catch (IOException e) {}
        }
        setItensTabela();
        setInfo();
    }

    @FXML
    private Label usernameLabel;
    @FXML
    private Label saldoLabel;
    @FXML
    private Label investidoLabel;
    @FXML
    private Label plLabel;
    @FXML
    private Label ganhosLabel;
    @FXML
    private Label ganhosPerLabel;
    public void setInfo() {
        usernameLabel.setText(pessoa.getUsername());
        saldoLabel.setText(AcaoListada.FormatD(pessoa.getMoney()));
        investidoLabel.setText("R$"+AcaoListada.FormatD(pessoa.ativosCount()));
        plLabel.setText("R$"+AcaoListada.FormatD(pessoa.ativosCount() + pessoa.getMoney()));
        ganhosLabel.setText("R$"+AcaoListada.FormatD((pessoa.ativosCount() + pessoa.getMoney()) - 5000));
        ganhosPerLabel.setText(AcaoListada.FormatD((((pessoa.ativosCount() + pessoa.getMoney()) - 5000) * 100) / 5000));
    }
}
