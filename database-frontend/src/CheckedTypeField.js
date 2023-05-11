import React from "react";
import PropTypes from "prop-types";

class CheckedTypeField extends React.Component{

    render() {
        const formNumber = this.props.formNumber;
        const fieldNumber = this.props.fieldNumber;
        const changeForm = this.props.changeForm;
        const changeValidity = this.props.changeValidity;
        const fieldName = this.props.nameField;
        const label = this.props.label;
        const placeholder = this.props.placeholder;
        const isValid = this.props.isValid;
        const currentNumber = this.props.currentNumber;
        return (
            <div>
                <label htmlFor={`${fieldName}Field-${formNumber}-${fieldNumber}`} className={"form-label"}>
                    {label}
                </label>
                <div className={"input-group"}>
                    <div className={"input-group-text form-check"}>
                        <input className="form-check-input" type="radio" name={`${formNumber}-${fieldNumber}-isValid`} id={`Check-${fieldName}Field-${formNumber}-${fieldNumber}`} onChange={(event) => {changeValidity(event, fieldNumber,currentNumber)}} checked={currentNumber === isValid}/>
                    </div>
                    <input id={`${fieldName}Field-${formNumber}-${fieldNumber}`} type={"text"} className={"form-control"} name={fieldName} placeholder={placeholder} onChange={(event) => {changeForm(event, fieldNumber)}} disabled={currentNumber !== isValid}/>
                </div>
            </div>
        );
    }
}

CheckedTypeField.propTypes = {
    formNumber : PropTypes.number.isRequired,
    fieldNumber : PropTypes.number.isRequired,
    changeForm : PropTypes.func.isRequired,
    changeValidity: PropTypes.func.isRequired,
    nameField : PropTypes.string.isRequired,
    label : PropTypes.string.isRequired,
    placeholder : PropTypes.string.isRequired,
    isValid : PropTypes.number.isRequired,
    currentNumber : PropTypes.number.isRequired,
}

export default CheckedTypeField;