import React from "react";
import PropTypes from "prop-types"
import TypeField from "./TypeField";
import SendButton from "./SendButton";
import AddButton from "./AddButton";
import DataFieldRange from "./DataFieldRange";
import ValidTypeField from "./ValidTypeField";
import ValidDatePicker from "./ValidDatePicker";
import RequestType from "./RequestType";
import DynamicTable from "./DynamicTable";

class Form17 extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            headerNames : ["Статус запроса", "Сообщение"],
            rowArray : [{operationStatus : "", message : ""}],

            requestType : {value : "insert"},

            testingId : {isValid : false, value : ""},
            unitId : {isValid : false, value : ""},
            testLabName : {isValid : false, value : ""},
            testerId : {isValid : false, value : ""},
            testPhaseName : {isValid : false, value : ""},
            testEquipmentId : {isValid : false, value : ""},
            startDate : {isValid : false, value : new Date().toISOString()},
            endDate : {isValid : false, value : new Date().toISOString()}
        }
    }

    handleRequestTypeChange = (event, requestType) => {
        this.setState({[event.target.name] : {isValid : this.state[event.target.name]["isValid"], value : requestType}});
    }

    handleFormChange = (event) => {
        this.setState({[event.target.name] : {isValid : this.state[event.target.name]["isValid"], value : event.target.value}});
    }

    handleDateChange = (date, name) =>{
        this.setState({[name] : {isValid : this.state[name]["isValid"], value : date.toISOString()}});
    }

    handleValidity = (event) => {
        this.setState({[event.target.name] : {isValid : !this.state[event.target.name]["isValid"], value : this.state[event.target.name]["value"]}});
    }

    sendForm = (event) => {
        event.preventDefault();
        const formType = this.props.formType;
        fetch(`http://localhost:8080/form/16`,
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
                            <h4>Операция тестирования</h4>
                        </label>
                        <div id={`TypeCompanyField-${formNumber}`}>
                            <div>
                                <RequestType formNumber={formNumber} fieldNumber={0} changeForm={this.handleRequestTypeChange} nameField={"requestType"} currentValue={this.state.requestType.value}></RequestType>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"testingId"} label={"Уникальный ключ операции тестирования:"} placeholder={"Введите уникальный ключ для редактирования, иначе выполнится вставка"} changeValidity={this.handleValidity} valid={this.state.testingId.isValid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"unitId"} label={"Уникальный ключ изделия:"} placeholder={"Введите ключ изделия"} changeValidity={this.handleValidity} valid={this.state.unitId.isValid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"testLabName"} label={"Имя лаборатории тестирования:"} placeholder={"Введите имя лаборатории тестирования"} changeValidity={this.handleValidity} valid={this.state.testLabName.isValid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"testerId"} label={"Имя тестировщика:"} placeholder={"Введите имя тестировщика"} changeValidity={this.handleValidity} valid={this.state.testerId.isValid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"testPhaseName"} label={"Имя тестовой фазы:"} placeholder={"Введите имя тестовой фазы"} changeValidity={this.handleValidity} valid={this.state.testPhaseName.isValid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"testEquipmentId"} label={"Ключ тестового оборудования:"} placeholder={"Введите ключ тестового оборудования"} changeValidity={this.handleValidity} valid={this.state.testEquipmentId.isValid}></ValidTypeField>
                                <ValidDatePicker formNumber={formNumber} fieldNumber={0} changeForm={this.handleDateChange} changeValidity={this.handleValidity} valid={this.state.startDate.isValid} nameField={"startDate"} label={"Дата начала тестирования:"}></ValidDatePicker>
                                <ValidDatePicker formNumber={formNumber} fieldNumber={0} changeForm={this.handleDateChange} changeValidity={this.handleValidity} valid={this.state.endDate.isValid} nameField={"endDate"} label={"Дата конца тестирования:"}></ValidDatePicker>
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

Form17.propTypes = {
    formNumber : PropTypes.number.isRequired,
    formType : PropTypes.number.isRequired,
};

export default Form17;