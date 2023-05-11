import React from "react";
import PropTypes from "prop-types";
import Form11 from "./Form11";
import Form12 from "./Form12";
import Form13 from "./Form13";
import Form14 from "./Form14";
import Form20 from "./Form20";
import Form15 from "./Form15";
import Form16 from "./Form16";
import Form17 from "./Form17";
import Form18 from "./Form18";
import Form19 from "./Form19";
import Form21 from "./Form21";

class EditPage extends React.Component
{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <>
                <nav>
                    <div className="nav nav-tabs bg-dark navbar-expand-lg bg-body-tertiary" id="nav-tab-edit" role="tablist">
                        <button className="btn btn-dark text-light nav-item active" id="nav-form11-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form11" type="button" role="tab" aria-controls="nav-form11"
                                aria-selected="true">Изделие
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form12-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form12" type="button" role="tab" aria-controls="nav-form12"
                                aria-selected="false">Бригада
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form13-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form13" type="button" role="tab" aria-controls="nav-form13"
                                aria-selected="false">Рабочий
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form14-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form14" type="button" role="tab" aria-controls="nav-form14"
                                aria-selected="false">Цех предприятия
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form20-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form20" type="button" role="tab" aria-controls="nav-form20"
                                aria-selected="false">Участок цеха предприятия
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form15-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form15" type="button" role="tab" aria-controls="nav-form15"
                                aria-selected="false">Лаборатория тестирования
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form16-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form16" type="button" role="tab" aria-controls="nav-form16"
                                aria-selected="false">Фаза изготовления
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form17-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form17" type="button" role="tab" aria-controls="nav-form17"
                                aria-selected="false">Фаза тестирования
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form18-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form18" type="button" role="tab" aria-controls="nav-form18"
                                aria-selected="false">Работники участка
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form19-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form19" type="button" role="tab" aria-controls="nav-form19"
                                aria-selected="false">Работники тестовой лаборатории
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form21-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form21" type="button" role="tab" aria-controls="nav-form21"
                                aria-selected="false">Тестировочное оборудование
                        </button>
                    </div>
                </nav>
                <div className="tab-content" id="nav-tabContent">
                    <div className="tab-pane fade show active" id="nav-form11" role="tabpanel" aria-labelledby="nav-form11-tab"
                         tabIndex="10">
                        <Form11 formNumber={10} formType={10}></Form11>
                    </div>
                    <div className="tab-pane fade" id="nav-form12" role="tabpanel" aria-labelledby="nav-form12-tab"
                         tabIndex="11">
                        <Form12 formNumber={11} formType={11}></Form12>
                    </div>
                    <div className="tab-pane fade" id="nav-form13" role="tabpanel" aria-labelledby="nav-form13-tab"
                         tabIndex="12">
                        <Form13 formNumber={12} formType={12}></Form13>
                    </div>
                    <div className="tab-pane fade" id="nav-form14" role="tabpanel" aria-labelledby="nav-form14-tab"
                         tabIndex="13">
                        <Form14 formNumber={13} formType={13}></Form14>
                    </div>
                    <div className="tab-pane fade" id="nav-form15" role="tabpanel" aria-labelledby="nav-form15-tab"
                         tabIndex="14">
                        <Form15 formNumber={14} formType={14}></Form15>
                    </div>
                    <div className="tab-pane fade" id="nav-form16" role="tabpanel" aria-labelledby="nav-form16-tab"
                         tabIndex="15">
                        <Form16 formNumber={15} formType={15}></Form16>
                    </div>
                    <div className="tab-pane fade" id="nav-form17" role="tabpanel" aria-labelledby="nav-form17-tab"
                         tabIndex="16">
                        <Form17 formNumber={16} formType={16}></Form17>
                    </div>
                    <div className="tab-pane fade" id="nav-form18" role="tabpanel" aria-labelledby="nav-form18-tab"
                         tabIndex="17">
                        <Form18 formNumber={17} formType={17}></Form18>
                    </div>
                    <div className="tab-pane fade" id="nav-form19" role="tabpanel" aria-labelledby="nav-form19-tab"
                         tabIndex="18">
                        <Form19 formNumber={18} formType={18}></Form19>
                    </div>
                    <div className="tab-pane fade" id="nav-form20" role="tabpanel" aria-labelledby="nav-form20-tab"
                         tabIndex="19">
                        <Form20 formNumber={19} formType={19}></Form20>
                    </div>
                    <div className="tab-pane fade" id="nav-form21" role="tabpanel" aria-labelledby="nav-form21-tab"
                         tabIndex="20">
                        <Form21 formNumber={20} formType={20}></Form21>
                    </div>
                </div>
            </>
        );
    }
}

EditPage.propTypes = {

};

export default EditPage;