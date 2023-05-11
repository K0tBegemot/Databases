import React from "react";
import PropTypes from "prop-types";
import DatePicker from "react-widgets/DatePicker";
import "react-widgets/styles.css";
import {disabled} from "react-widgets/PropTypes";

class DataFieldRange extends React.Component{

    render() {
        const formNumber = this.props.formNumber;
        const fieldNumber = this.props.fieldNumber;
        const changeForm = this.props.changeForm;
        const changeValidity = this.props.changeValidity;
        const fieldName = this.props.nameField;
        const label = this.props.label;
        const isValid = this.props.isValid;

        let startDate, endDate;

        return(
            <>
                <label className={"form-label"} htmlFor={`${fieldName}Field-${formNumber}-${fieldNumber}`}>{label}</label>
                <div className={"input-group"} id={`${fieldName}Field-${formNumber}-${fieldNumber}`}>
                    <div className={"input-group-text"}>
                        <input className={"form-check-input"} type={"checkbox"} value={"false"} aria-label={label} name={"isValid"} onChange={(event) => {changeValidity(event, fieldNumber)}}/>
                    </div>
                        <DatePicker defaultValue={new Date()} id={`StartDateField-${formNumber}-${fieldNumber}`} name={"startDate"} onChange={(event) => {changeForm(event, "startDate")}} disabled={!isValid}>
                        </DatePicker>
                        <DatePicker defaultValue={new Date()} id={`EndDateField-${formNumber}-${fieldNumber}`} name={"endDate"} onChange={(event) => {changeForm(event, "endDate")}} disabled={!isValid}>
                        </DatePicker>
                </div>
            </>
        );
    }
}

DataFieldRange.propTypes = {
    formNumber : PropTypes.number.isRequired,
    fieldNumber : PropTypes.number.isRequired,
    changeForm : PropTypes.func.isRequired,
    changeValidity : PropTypes.func.isRequired,
    nameField : PropTypes.string.isRequired,
    label : PropTypes.string.isRequired,
    isValid : PropTypes.bool.isRequired
}

export default DataFieldRange;