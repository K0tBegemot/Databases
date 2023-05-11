import React from "react";
import NavigationBar from "./NavigationBar";
import TabbedMain from "./TabbedMain";

class Site extends React.Component{
    constructor(props, context) {
        super(props, context);
    }

    render() {
        return (
            <>
                <TabbedMain></TabbedMain>
            </>
        );
    }

}

export default Site;