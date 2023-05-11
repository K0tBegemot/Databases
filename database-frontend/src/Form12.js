import React from "react";
import PropTypes from "prop-types"
import TypeField from "./TypeField";
import SendButton from "./SendButton";
import AddButton from "./AddButton";
import DataFieldRange from "./DataFieldRange";
import ValidTypeField from "./ValidTypeField";
import RequestType from "./RequestType";
import DynamicTable from "./DynamicTable";

class Form12 extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            headerNames : ["Статус запроса", "Сообщение"],
            rowArray : [{operationStatus : "", message : ""}],

            requestType : {value : "insert"},

            brigadeId : {valid : false, value : ""},
            foremanId : {valid : false, value : ""},
            workshopName : {valid : false, value : ""},
            workshopSectionName : {valid : false, value : ""}
        }
    }

    handleRequestTypeChange = (event, requestType) => {
        this.setState({[event.target.name] : {valid : this.state[event.target.name]["valid"], value : requestType}});
    }

    handleFormChange = (event) => {
        this.setState({[event.target.name] : {valid : this.state[event.target.name]["valid"], value : event.target.value}});
    }

    handleValidity = (event) => {
        this.setState({[event.target.name] : {valid : !this.state[event.target.name]["valid"], value : this.state[event.target.name]["value"]}});
    }

    sendForm = (event) => {
        event.preventDefault();
        const formType = this.props.formType;
        fetch(`http://localhost:8080/form/11`,
            {
                method : "POST",
                mode : "cors",
                cache : "default",
                headers : {
                    "Content-Type" : "application/json",
                },
                body : JSON.stringify(this.state)
            }
        ).then((response) => {return response.json()}).then((response) => {
            console.log(response);
            this.setState({rowArray : [response]});
        });
    }

    render() {
        const formNumber = this.props.formNumber;
        return (
            <>
                <form className={"row justify-content-center"}>
                    <div className={"row col-6 shadow-lg rounded-2"}>
                        <label htmlFor={`TypeCompanyField-${formNumber}`} className={"form-label"}>
                            <h4>Уникальный идентификатор бригады, бригадир, цех, участок</h4>
                        </label>
                        <div id={`TypeCompanyField-${formNumber}`}>
                            <div>
                                <RequestType formNumber={formNumber} fieldNumber={0} changeForm={this.handleRequestTypeChange} nameField={"requestType"} currentValue={this.state.requestType.value}></RequestType>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"brigadeId"} label={"Уникальный ключ бригады:"} placeholder={"Введите уникальный ключ для редактирования, иначе выполнится вставка"} changeValidity={this.handleValidity} valid={this.state.brigadeId.valid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"foremanId"} label={"Уникальный ключ бригадира:"} placeholder={"Введите уникальный ключ бригадира"} changeValidity={this.handleValidity} valid={this.state.foremanId.valid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"workshopName"} label={"Цех предприятия:"} placeholder={"Введите имя цеха предприятия"} changeValidity={this.handleValidity} valid={this.state.workshopName.valid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"workshopSectionName"} label={"Участок цеха предприятия:"} placeholder={"Введите имя участка цеха предприятия"} changeValidity={this.handleValidity} valid={this.state.workshopSectionName.valid}></ValidTypeField>
                                <div className={"row"}>
                                    <div className={"col"}>
                                        <SendButton sendForm={this.sendForm}></SendButton>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <DynamicTable headerNamesList={this.state.headerNames} BodyValues={this.state.rowArray}></DynamicTable>
                    </div>
                </form>
            </>
        );
    }
}

Form12.propTypes = {
    formNumber : PropTypes.number.isRequired,
    formType : PropTypes.number.isRequired,
};

export default Form12;