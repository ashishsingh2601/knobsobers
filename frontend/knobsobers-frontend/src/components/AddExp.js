import React, {useState} from 'react';
import {Button, Drawer, Input, InputNumber} from 'antd';  

const AddExp = () => {

    const [visible, setVisible] = useState(false);
        const showDrawer = () => {
            setVisible(true);
        };
        const onClose = () => {
            setVisible(false);
    };
    const { TextArea } = Input;
        const onChange = e => {
        console.log(e);
    };

    // const [years, setYears] = useState(0);
    function onInputNumberChange(value) {
        console.log('changed', value);

    }


    return (
        <>
            <Button type="secondary" onClick={showDrawer}  style={{ boxShadow: '5px 5px 5px rgba(29, 29, 29, 0.733)'}}>Add Experience</Button>
            <Drawer title="Add Experience" placement="right" width={600} onClose={onClose} visible={visible}>
                <h5>Pen Down your Experience</h5>
                <div className="exp-input">
                    <Input placeholder="Enter your Name" className="exp-input-field" allowClear onChange={onChange} />
                    <Input placeholder="Enter your Domain" className="exp-input-field" allowClear onChange={onChange} />
                    <Input placeholder="Enter your Company Name" className="exp-input-field" allowClear onChange={onChange} />
                    <div className="exp-input-field" style={{display:'flex', justifyContent: 'space-between'}}>
                        <span>Experience:</span>
                        <label for="year-input">Year(s)</label>
                        <InputNumber type="number" min={0} max={60} defaultValue={0} onChange={onInputNumberChange} />
                        <label for="month-input">Month(s)</label>
                        <InputNumber type="number" min={0} max={11} defaultValue={0} onChange={onInputNumberChange} />
                        {/* <Button
                            type="primary"
                            onClick={() => {
                            setYears(0);
                            }}
                        >
                            Reset
                        </Button> */}

                    </div>
                    <TextArea placeholder="Describe your experience..."  className="exp-input-field" allowClear onChange={onChange} />
                </div>
                <Button type="primary" style={{ boxShadow: '5px 5px 5px rgba(29, 29, 29, 0.733)'}}>Submit</Button>
            </Drawer>
        </>
    )
}

export default AddExp
