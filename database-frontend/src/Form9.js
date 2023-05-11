import React from "react";
import PropTypes from "prop-types"
import TypeField from "./TypeField";
import SendButton from "./SendButton";
import AddButton from "./AddButton";
import DataFieldRange from "./DataFieldRange";
import CheckedTypeField from "./CheckedTypeField";
import DynamicTable from "./DynamicTable";

class Form9 extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            headerNames : ["Статус запроса", "Сообщение"],
            rowArray : [{operationStatus : "", message : ""}],

            headerNames2 : ["Идентификационный номер тестировщика", "Имя", "Фамилия"],
            rowArray2 : [{testerId : "", firstName : "", secondName : ""}],

            typeCompanyFields : [{labNumber : "Все" , type : "Все", number : "Все", isValid : 1}]
        }
    }

    handleFormChange = (event, index) => {
        let data = [...this.state.typeCompanyFields];
        data[index][event.target.name] = event.target.value;
        this.setState({typeCompanyFields : data});
    }

    handleValidityChange = (event, index, isValid) => {
        let data = [...this.state.typeCompanyFields];
        data[index]['isValid'] = isValid;
        this.setState({typeCompanyFields : data});
    }

    sendForm = (event) => {
        event.preventDefault();
        const data = this.state;
        const formType = this.props.formType;
        fetch(`http://localhost:8080/form/8`,
            {
                method : "POST",
                mode : "cors",
                cache : "default",
                headers : {
                    "Content-Type" : "application/json",
                },
                body : JSON.stringify({typeCompanyFields : this.state.typeCompanyFields})
            }
        ).then((response) => {return response.json()}).then((response) => {
            console.log(response);
            this.setState({rowArray : response["statuses"]["statuses"], rowArray2 : response["testers"]});
        });
    }

    addNewField = (event) => {
        event.preventDefault();
        let data = [...this.state.typeCompanyFields, {labNumber : "" , type : "Все", number : "", isValid : 1}];
        this.setState({typeCompanyFields : data});
    }

    render() {
        const formNumber = this.props.formNumber;
        return (
            <>
                <form className={"row justify-content-center"}>
                    <div className={"row col-6 shadow-lg rounded-2"}>
                        <label htmlFor={`TypeCompanyField-${formNumber}`} className={"form-label"}>
                            <h4>Номер лаборатории:</h4>
                        </label>
                        <div id={`EmployeeWorkshopField-${formNumber}`}>
                            <div>
                                {this.state.typeCompanyFields.map((value, index) => {
                                    return(
                                        <>
                                            <CheckedTypeField formNumber={formNumber} fieldNumber={index} currentNumber={0} changeForm={this.handleFormChange} changeValidity={this.handleValidityChange} nameField={"labNumber"} label={"Номер лаборатории: "} placeholder={"Введите номер лаборатории: "} isValid={value.isValid}></CheckedTypeField>
                                            <CheckedTypeField formNumber={formNumber} fieldNumber={index} currentNumber={1} changeForm={this.handleFormChange} changeValidity={this.handleValidityChange} nameField={"type"} label={"Вид продукции: "} placeholder={"Все"} isValid={value.isValid}></CheckedTypeField>
                                            <CheckedTypeField formNumber={formNumber} fieldNumber={index} currentNumber={2} changeForm={this.handleFormChange} changeValidity={this.handleValidityChange} nameField={"number"} label={"Номер изделия: "} placeholder={"Введите номер изделия: "} isValid={value.isValid}></CheckedTypeField>
                                        </>
                                    )
                                })}
                                <div className={"row"}>
                                    <div className={"col"}>
                                        <AddButton addData={this.addNewField}></AddButton>
                                    </div>
                                    <div className={"col"}>
                                        <SendButton sendForm={this.sendForm}></SendButton>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <DynamicTable headerNamesList={this.state.headerNames} BodyValues={this.state.rowArray}></DynamicTable>
                        <DynamicTable headerNamesList={this.state.headerNames2} BodyValues={this.state.rowArray2}></DynamicTable>
                    </div>
                </form>
            </>
        );
    }
}

Form9.propTypes = {
    formNumber : PropTypes.number.isRequired,
    formType : PropTypes.number.isRequired,
};

export default Form9;