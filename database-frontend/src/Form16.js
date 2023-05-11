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

class Form16 extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            headerNames : ["Статус запроса", "Сообщение"],
            rowArray : [{operationStatus : "", message : ""}],

            requestType : {value : "insert"},

            productionOperationId : {isValid : false, value : ""},
            unitId : {isValid : false, value : ""},
            workshopName : {isValid : false, value : ""},
            workshopSectionName : {isValid : false, value : ""},
            brigadeId : {isValid : false, value : ""},
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
        fetch(`http://localhost:8080/form/15`,
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
                            <h4>Производственная операция</h4>
                        </label>
                        <div id={`TypeCompanyField-${formNumber}`}>
                            <div>
                                <RequestType formNumber={formNumber} fieldNumber={0} changeForm={this.handleRequestTypeChange} nameField={"requestType"} currentValue={this.state.requestType.value}></RequestType>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"productionOperationId"} label={"Уникальный ключ операции производства:"} placeholder={"Введите уникальный ключ для редактирования, иначе выполнится вставка"} changeValidity={this.handleValidity} valid={this.state.productionOperationId.isValid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"unitId"} label={"Уникальный ключ изделия:"} placeholder={"Введите ключ изделия"} changeValidity={this.handleValidity} valid={this.state.unitId.isValid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"workshopName"} label={"Имя цеха:"} placeholder={"Введите имя цеха"} changeValidity={this.handleValidity} valid={this.state.workshopName.isValid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"workshopSectionName"} label={"Имя участка цеха:"} placeholder={"Введите имя участка цеха"} changeValidity={this.handleValidity} valid={this.state.workshopSectionName.isValid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"brigadeId"} label={"Уникальный ключ бригады:"} placeholder={"Введите уникальный ключ бригады"} changeValidity={this.handleValidity} valid={this.state.brigadeId.isValid}></ValidTypeField>
                                <ValidDatePicker formNumber={formNumber} fieldNumber={0} changeForm={this.handleDateChange} changeValidity={this.handleValidity} valid={this.state.startDate.isValid} nameField={"startDate"} label={"Дата начала операции:"}></ValidDatePicker>
                                <ValidDatePicker formNumber={formNumber} fieldNumber={0} changeForm={this.handleDateChange} changeValidity={this.handleValidity} valid={this.state.endDate.isValid} nameField={"endDate"} label={"Дата конца операции:"}></ValidDatePicker>
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

Form16.propTypes = {
    formNumber : PropTypes.number.isRequired,
    formType : PropTypes.number.isRequired,
};

export default Form16;