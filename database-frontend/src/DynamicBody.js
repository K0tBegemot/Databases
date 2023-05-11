import React from "react";
import PropTypes from "prop-types";

class DynamicBody extends React.Component
{
    render() {
        const list = this.props.BodyValues;
        const bodyRows = list.map((value, index) => {
            const keysArray = Object.keys(value);
            let dataArray = [];
            let minLength = (keysArray.length < this.props.MaxColumnNumber) ? keysArray.length : this.props.MaxColumnNumber;
            for(let i = 0; i < minLength; i++)
            {
                dataArray.push(<td>{value[keysArray[i]]}</td>);
            }
            return(
                <tr>
                    {dataArray}
                </tr>
            );
        });
        return (
            <tbody>
                {bodyRows}
            </tbody>
        );
    }
}

DynamicBody.propTypes = {
    BodyValues : PropTypes.array.isRequired,
    MaxColumnNumber : PropTypes.number.isRequired
}

export default DynamicBody;