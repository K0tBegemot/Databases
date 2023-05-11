import React from "react";
import PropTypes from "prop-types"
import TypeField from "./TypeField";
import SendButton from "./SendButton";
import AddButton from "./AddButton";
import DataFieldRange from "./DataFieldRange";
import DynamicTable from "./DynamicTable";

class Form6 extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            headerNames : ["Статус запроса", "Сообщение"],
            rowArray : [{operationStatus : "", message : ""}],

            headerNames2 : ["Идентификационный номер изделия", "Идентификационный номер работника", "Идентификационный номер бригады", "Имя работника", "Фамилия работника"],
            rowArray2 : [{unitId : "", workerId : "", brigadeId : "", firstName : "", secondName : ""}],

            typeCompanyFields : [{productNumber : "Все"}]
        }
    }

    handleFormChange = (event, index) => {
        let data = [...this.state.typeCompanyFields];
        data[index][event.target.name] = event.target.value;
        this.setState({typeCompanyFields : data});
    }

    sendForm = (event) => {
        event.preventDefault();
        const data = this.state;
        const formType = this.props.formType;
        fetch(`http://localhost:8080/form/5`,
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
            this.setState({rowArray : [response["status"]], rowArray2 : response["brigadeList"]});
        });
    }

    addNewField = (event) => {
        event.preventDefault();
        let data = [...this.state.typeCompanyFields, {productNumber : "Все"}];
        this.setState({typeCompanyFields : data});
    }

    render() {
        const formNumber = this.props.formNumber;
        return (
            <>
                <form className={"row justify-content-center"}>
                    <div className={"row col-6 shadow-lg rounded-2"}>
                        <label htmlFor={`TypeCompanyField-${formNumber}`} className={"form-label"}>
                            <h4>Номер(а) изделия(й):</h4>
                        </label>
                        <div id={`EmployeeWorkshopField-${formNumber}`}>
                            <div>
                                {this.state.typeCompanyFields.map((value, index) => {
                                    return(
                                        <>
                                            <TypeField formNumber={formNumber} fieldNumber={index} changeForm={this.handleFormChange} nameField={"productNumber"} label={"Номер продукта:"} placeholder={"Введите номер продукта:"}></TypeField>
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

Form6.propTypes = {
    formNumber : PropTypes.number.isRequired,
    formType : PropTypes.number.isRequired,
};

export default Form6;