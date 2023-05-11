import React from "react";
import PropTypes from "prop-types";

class DynamicHeaders extends React.Component
{
    render() {
        const list = this.props.headerNamesList;
        const retHeaders = list.map((value, index) => {
            return (
                <th>
                    {value}
                </th>
            );
        });
        return (
            <thead>
                <tr>
                    {retHeaders}
                </tr>
            </thead>
        );
    }
}

DynamicHeaders.propTypes = {
    headerNamesList : PropTypes.array.isRequired,
}

export default DynamicHeaders;