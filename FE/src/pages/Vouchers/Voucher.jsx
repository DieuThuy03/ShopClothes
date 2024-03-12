import React, { useState, useEffect } from 'react';
import {
    Table, Space, Button, Input, Form, Modal,
    notification, Radio, Row, Select, DatePicker,
    InputNumber, Col, Tag, Popconfirm, Card, Switch,
    Popover
} from 'antd';
import {
    PlusOutlined,
    RedoOutlined,
    FormOutlined,
    DeleteOutlined,
    SearchOutlined,
    FileDoneOutlined,
    FilterOutlined,
} from '@ant-design/icons';
import './Voucher.css'
import VoucherService from '~/service/VoucherService';
import FormatDate from '~/utils/format-date';
import { fomatVoucherDate } from 'utils/voucherFormatDate';
import dayjs from 'dayjs';
import formatCurrency from '~/utils/format-currency';
const { TextArea } = Input;
const { Option } = Select;

function Voucher() {

    const [loading, setLoading] = useState(false);

    const [open, setOpen] = useState({ isModal: false, isMode: '', reacord: null });

    const showModal = (mode, record) => {
        setOpen({
            isModal: true,
            isMode: mode,
            record: record,
            reacord: record,
        });
    };

    const hideModal = () => {
        setOpen({ isModal: false });
    };

    const [vouchers, setVouchers] = useState([]);

    const [searchCode, setSearchCoode] = useState(null);
    const [searchStatus, setSearchStatus] = useState(null);


    const [pagination, setPagination] = useState({ current: 1, pageSize: 5, total: 0 });

    const [filterVoucher, setFilterVoucher] = useState({
        keyword: null,
        createdAtStart: null,
        createdAtEnd: null,
        status: null,
        pageNo: 0,
        pageSize: 5
    });
    const fetchVouchers = async () => {

        try {
            const response = await VoucherService.getAll(
                pagination.current - 1,
                pagination.pageSize,
                searchCode,
                searchStatus
            );
            setLoading(true);
            console.log('Reponse', response);
            console.log('Status', response.status);
            console.log('Data', response.data);

            if (response && response.data) {
                const status = response.status || (response.data && response.data.status);

                if (status === 200) {
                    const responseData = response.data;

                    if (Array.isArray(responseData)) {
                        console.log('Response Data:', response);
                        const formattedVouchers = responseData.map(vocherResponse => ({
                            key: vocherResponse.id,
                            id: vocherResponse.id,
                            code: vocherResponse.code,
                            name: vocherResponse.name,
                            reducedValue: vocherResponse.reducedValue,
                            startTime: fomatVoucherDate(vocherResponse.startTime),
                            endTime: fomatVoucherDate(vocherResponse.endTime),
                            quantity: vocherResponse.quantity,
                            minimumOrder: vocherResponse.minimumOrder,
                            minimize: vocherResponse.minimize,
                            dateCreate: new Date(vocherResponse.dateCreate).toLocaleString(),
                            dateUpdate: vocherResponse.dateUpdate ? new Date(vocherResponse.dateUpdate).toLocaleString() : 'N/A',
                            describe: vocherResponse.describe,
                            status: vocherResponse.status?.moTa,
                        }));
                        setVouchers(formattedVouchers);
                        setPagination({
                            ...pagination,
                            total: response.totalCount,
                        });
                    } else {
                        console.error('Dữ liệu không phải là một mảng.');

                    }
                } else {
                    console.error('Trạng thái không thành công: ', status);
                }
            } else {
                console.error('Không có response hoặc response.data.');
            }
        } catch ({ response, message }) {
            console.error('Lỗi khi gọi API: ', response || message);
        } finally {
            // ...
        }
    };


    useEffect(() => {
        console.log("Fetching voucher...");
        fetchVouchers();
    }, [pagination.current, pagination.pageSize, searchCode, searchStatus]);

    const handleTableChange = (pagination) => {
        setPagination({
            ...pagination,
        });
        setFilterVoucher({
            ...filterVoucher,
            pageNo: pagination.current - 1,
            pageSize: pagination.pageSize,
        });
    };
    const handleFilterVoucherChange = (property, value) => {
        setFilterVoucher({
            ...filterVoucher,
            [property]: value,
            pageNo: 0,
        });
    };
    const [searchKeywordVoucher, setSearchKeywordVoucher] = useState(null);

    const handleSearchVoucher = () => {
        setFilterVoucher({
            ...filterVoucher,
            keyword: searchKeywordVoucher,
            pageNo: 0,
        });
    };
    const handleResetVoucher = () => {
        setSearchKeywordVoucher(null)
        setFilterVoucher({
            keyword: null,
            createdAtStart: null,
            createdAtEnd: null,
            status: null,
            pageNo: 0,
            pageSize: 10
        });
        setPagination({
            ...pagination,
            current: 1,
            pageSize: 5,
        });
    };
    const handleDelete = async (id) => {
        try {
            console.log("Deleting record with ID:", id);
            await VoucherService.updateStatus(id);
            fetchVouchers();
        } catch (error) {
            console.error(error);
            notification.error({
                message: 'Thông báo',
                description: 'Đã xảy ra lỗi!',
            });
        }

    };

    const columns = [
        {
            title: '#',
            dataIndex: 'key',
            key: 'key',
            width: '4%',
            render: (value, item, index) => (pagination.current - 1) * pagination.pageSize + index + 1
        },
        {
            title: 'Mã',
            dataIndex: 'code',
            key: 'code',
            width: '7%',

        },
        {
            title: 'Tên',
            dataIndex: 'name',
            key: 'name',
            width: '10%',
            sorter: true,
        },

        {
            title: 'Giảm Tri giá',
            dataIndex: 'reducedValue',
            key: 'reducedValue',
            width: '8%',
            sorter: (a, b) => a.reducedValue - b.reducedValue,
            render: (text) => <span>{formatCurrency(text)}</span>,
        },
        {
            title: 'Ngày bắt đầu',
            dataIndex: 'startTime',
            key: 'startTime',
            width: '9%',
        },
        {
            title: 'Ngày kết thúc',
            dataIndex: 'endTime',
            key: 'endTime',
            width: '9%',
        },
        {
            title: 'Số lượng',
            dataIndex: 'quantity',
            key: 'quantity',
            width: '9%',
            sorter: (a, b) => a.quantity - b.quantity,
        },
        {
            title: 'Đơn tối thiểu',
            dataIndex: 'minimumOrder',
            key: 'minimumOrder',
            width: '9%',
            sorter: (a, b) => a.minimumOrder - b.minimumOrder,
            render: (text) => <span>{formatCurrency(text)}</span>,
        },
        {
            title: 'Giảm tối đa',
            dataIndex: 'minimize',
            key: 'minimize',
            width: '9%',
            sorter: (a, b) => a.minimize - b.minimize,
            render: (text) => <span>{formatCurrency(text)}</span>,
        },
        {
            title: 'Ghi Chú',
            dataIndex: 'describe',
            key: 'describe',
            width: '9%',
        },

        {
            title: 'Trạng thái',
            key: 'status',
            dataIndex: 'status',
            width: '10%',
            // filters: [
            //     {
            //         text: 'Sắp diễn ra ',
            //         value: 0,
            //     },
            //     {
            //         text: 'Đang diễn ra',
            //         value: 1,
            //     },
            //     {
            //         text: 'Sắp hết hạn',
            //         value: 2,
            //     },
            //     {
            //         text: 'Hết hạn',
            //         value: 3,
            //     },
            //     {
            //         text: 'Đã hết',
            //         value: 4,
            //     },
            //     {
            //         text: 'Hủy bỏ',
            //         value: 5,
            //     },
            // ],
            // onFilter: (value, record) => record.deleted === value,
            // render: (text) => (
            //     text == 0 ? <Tag style={{ borderRadius: '4px', fontWeight: '450', padding: '0 4px ' }} color="gold">Sắp diễn ra</Tag>
            //         : text == 1 ? <Tag style={{ borderRadius: '4px', fontWeight: '450', padding: '0 4px ' }} color="green">Đang diễn ra</Tag>
            //             : text == 2 ? <Tag style={{ borderRadius: '4px', fontWeight: '450', padding: '0 4px ' }} color="volcano">Sắp hết hạn</Tag>
            //                 : text == 3 ? <Tag style={{ borderRadius: '4px', fontWeight: '450', padding: '0 4px ' }} color="blue">Hết hạn</Tag>
            //                     : text == 4 ? <Tag style={{ borderRadius: '4px', fontWeight: '450', padding: '0 4px ' }} color="purple">Đã hết</Tag>
            //                         : <Tag style={{ borderRadius: '4px', fontWeight: '450', padding: '0 4px ' }} color="red">Hủy bỏ</Tag>

            // )
        },

        {
            title: 'Hành động',
            key: 'action',
            width: '7%',
            render: (record) => {

                return <Space size="middle">
                    <Button type="text"
                        icon={<FormOutlined style={{ color: 'rgb(214, 103, 12)' }} />}
                        onClick={() => showModal("edit", record)} />
                    confirm({

                        <Button
                            //size="small"
                            icon={<DeleteOutlined style={{ color: 'rgb(214, 103, 12)' }} />}
                            //defaultChecked={record.deleted}
                            onClick={() => record.id && handleDelete(record.id)}
                        />
                    })

                </Space>
            }

        },
    ];

    return (
        <>

            <Card
                title={<span style={{ color: '#5a76f3' }}><FilterOutlined /> Lọc</span>}
                style={{ borderRadius: '10px' }}
            >
                <Row>
                    <Col span={12} style={{ padding: '0 100px' }}>
                        <DatePicker
                            format="hh:mm - DD/MM/YYYY"
                            style={{
                                width: '100%',
                                height: '35px',
                                borderRadius: '5px',
                            }}
                            showTime

                            placeholder="Từ ngày"
                            value={filterVoucher.createdAtStart}
                            onChange={(value) => handleFilterVoucherChange('createdAtStart', value && dayjs(value).add(7, 'hour'))}
                        />
                    </Col>
                    <Col span={12} style={{ padding: '0 100px' }}>
                        <DatePicker
                            format="hh:mm - DD/MM/YYYY"
                            style={{
                                width: '100%',
                                height: '35px',
                                borderRadius: '5px',
                            }}
                            showTime
                            placeholder="Đến ngày"
                            value={filterVoucher.createdAtEnd}
                            onChange={(value) => handleFilterVoucherChange('createdAtEnd', value && dayjs(value).add(7, 'hour'))}
                        />
                    </Col>
                </Row>

                <Row style={{ marginTop: '20px' }}>
                    <Col span={12} style={{ padding: '0 100px' }}>
                        <Select
                            style={{
                                width: '100%',
                            }}
                            allowClear
                            placeholder="Trạng thái"
                            value={filterVoucher.status}
                            onChange={(value) => handleFilterVoucherChange('status', value)}
                            options={[
                                {
                                    value: true,
                                    label: 'Hoạt động',
                                },
                                {
                                    value: false,
                                    label: 'Hết hạn',
                                },
                            ]}
                        />
                    </Col>
                    <Col span={12} style={{ padding: '0 100px' }}>
                        <Input.Search

                            placeholder="Nhập mã, tên, mức giảm giá..."
                            value={searchKeywordVoucher}
                            onChange={(e) => setSearchKeywordVoucher(e.target.value)}
                            onSearch={handleSearchVoucher}
                        />
                    </Col>
                </Row>

            </Card>
            <Card
                title={<span style={{ color: '#5a76f3' }}><FileDoneOutlined />  Danh sách giảm giá</span>}
                style={{ marginTop: '25px', borderRadius: '10px' }}
            >
                <Button type="primary"
                    icon={<PlusOutlined />}
                    onClick={() => showModal("add")}
                    style={{ marginBottom: '5px', float: 'right', borderRadius: '2px' }} >
                    Thêm mới
                </Button>

                <Button type="primary"
                    icon={<RedoOutlined style={{ fontSize: '18px' }} />}
                    style={{ marginBottom: '5px', float: 'right', marginRight: '6px', borderRadius: '4px', }}
                    onClick={handleResetVoucher}
                />
                <Table
                    dataSource={vouchers}
                    onChange={handleTableChange}

                    // loading={loading}
                    columns={columns}
                    pagination={{
                        current: pagination.current,
                        pageSize: pagination.pageSize,
                        defaultPageSize: 5,
                        pageSizeOptions: ['5', '10', '15'],
                        total: pagination.total,
                        showSizeChanger: true,
                    }}></Table >
            </Card>
            {open.isModal && <VoucherModal
                isMode={open.isMode}
                reacord={open.reacord || {}}
                hideModal={hideModal}
                isModal={open.isModal}
                vouchers={vouchers}
                fetchVouchers={fetchVouchers}
            />}
        </>
    )
};
export default Voucher;


const VoucherModal = ({ isMode, reacord, hideModal, isModal, fetchVouchers, vouchers }) => {

    const [form] = Form.useForm();

    const handleCreate = () => {
        form.validateFields().then(async () => {

            const values = await form.validateFields();

            await VoucherService.create(values)
                .then(() => {
                    notification.success({
                        message: 'Thông báo',
                        description: 'Thêm mới thành công!',
                    });
                    fetchVouchers();
                    // Đóng modal
                    hideModal();
                })
                .catch(error => {
                    notification.error({
                        message: 'Thông báo',
                        description: 'Thêm mới thất bại!',
                    });
                    console.log('a: ' + error)
                    console.error(error);
                });

        }).catch(error => {
            console.error(error);
        })

    }
    const handleUpdate = () => {
        console.log('Record ID in handleUpdate:', reacord.id);
        // reacord.startTime = fomatVoucherDate(reacord.startTime)
        // reacord.endTime = fomatVoucherDate(reacord.endTime)

        form.validateFields().then(async () => {

            const data = form.getFieldsValue();
            console.log('Record ID in handleUpdate:', reacord.id);
            await VoucherService.update(reacord.id, data)
                .then(() => {
                    notification.success({
                        message: 'Thông báo',
                        description: 'Cập nhật thành công!',
                    });
                    fetchVouchers();
                    // Đóng modal
                    hideModal();
                })
                .catch(error => {
                    notification.error({
                        message: 'Thông báo',
                        description: 'Cập nhật thất bại!',
                    });
                    console.error(error);
                });


        }).catch(error => {
            console.error(error);
        })


    }
    const selectAfter = (
        <Select
            defaultValue="VND"
            style={{
                width: 90,
            }}
        >
            <Option value="VND">VND</Option>
            <Option value="%">%</Option>
        </Select>
    );



    const formatNumberToiThieu = (value) => {
        if (value === undefined || value === null) {
            return value;
        }

        const stringValue = String(value);
        const parts = stringValue.split('.');
        parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, '.');

        return parts.join('.');
    };
    const formatNumberToiDa = (value) => {
        if (value === undefined || value === null) {
            return value;
        }

        const stringValue = String(value);
        const parts = stringValue.split('.');
        parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, '.');

        return parts.join('.');
    };

    const formatGiamGia = (value) => {
        if (value === undefined || value === null) {
            return value;
        }

        const stringValue = String(value);
        const parts = stringValue.split('.');
        parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, '.');

        return parts.join('.');
    };

    return (
        <Row>
            <Col span={24}>
                <Modal
                    width={800}
                    title={isMode === "edit" ? "Cập nhật giảm giá" : "Thêm mới một giảm giá"}
                    open={isModal}
                    onOk={isMode === "edit" ? handleUpdate : handleCreate}
                    onCancel={hideModal}
                    okText={isMode === "edit" ? "Cập nhật" : "Thêm mới"}
                    cancelText="Hủy bỏ"
                >
                    <Form
                        name="validateOnly" layout="vertical" autoComplete="off"
                        form={form}
                    // initialValues={{

                    //     ...reacord,
                    // }
                    // }
                    >
                        <Row>
                            <Col span={11}>
                                <Form.Item label="Mã:" name="code" initialValue={reacord.code} rules={[{ required: false, message: 'Vui lòng nhập mã giảm giá!' }
                                    ,
                                {
                                    validator: (_, value) => {
                                        if (!value) {
                                            return Promise.resolve();
                                        }
                                        const trimmedValue = value.trim(); // Loại bỏ dấu cách ở đầu và cuối
                                        const lowercaseValue = trimmedValue.toLowerCase(); // Chuyển về chữ thường
                                        const isDuplicate = vouchers.some(
                                            (voucher) => voucher.code.trim().toLowerCase() === lowercaseValue && voucher.id !== reacord.id
                                        );
                                        if (isDuplicate) {
                                            return Promise.reject('Mã voucher đã tồn tại!');
                                        }
                                        // Kiểm tra dấu cách ở đầu và cuối
                                        if (/^\s|\s$/.test(value)) {
                                            return Promise.reject('Mã voucher không được chứa dấu cách ở đầu và cuối!');
                                        }
                                        return Promise.resolve();
                                    },
                                },
                                ]}>
                                    <Input placeholder="Nhập mã..." />
                                </Form.Item>
                            </Col>
                            <Col span={1}>
                            </Col>
                            <Col span={12}>
                                <Form.Item label="Tên:" name="name" initialValue={reacord.name} rules={[{ required: true, message: 'Vui lòng nhập tên giảm giá!' }
                                    ,
                                {
                                    validator: (_, value) => {
                                        if (!value) {
                                            return Promise.resolve();
                                        }
                                        const trimmedValue = value.trim(); // Loại bỏ dấu cách ở đầu và cuối
                                        const lowercaseValue = trimmedValue.toLowerCase(); // Chuyển về chữ thường
                                        const isDuplicate = vouchers.some(
                                            (voucher) => voucher.name.trim().toLowerCase() === lowercaseValue && voucher.id !== reacord.id
                                        );
                                        if (isDuplicate) {
                                            return Promise.reject('Tên voucher đã tồn tại!');
                                        }
                                        // Kiểm tra dấu cách ở đầu và cuối
                                        if (/^\s|\s$/.test(value)) {
                                            return Promise.reject('Tên voucher không được chứa dấu cách ở đầu và cuối!');
                                        }
                                        return Promise.resolve();
                                    },
                                },
                                ]}>
                                    <Input placeholder="Nhập tên..." />
                                </Form.Item>
                            </Col>
                        </Row>

                        <Row >
                            <Col span={11}>
                                <Form.Item
                                    label="Giá Trị Giảm:"
                                    name="reducedValue"
                                    initialValue={reacord.reducedValue}
                                    rules={[
                                        {
                                            required: true,
                                            type: 'number',
                                            min: 0,
                                            message: 'Vui lòng nhập mức giảm lớn hơn 0!',
                                        },
                                        {
                                            validator: (_, value) => {
                                                const intValue = parseInt(value, 10);
                                                if (!value) {
                                                    return Promise.resolve();
                                                }
                                                if (isNaN(intValue) || intValue <= 0) {
                                                    return Promise.reject(new Error('Vui lòng nhập số nguyên dương!'));
                                                }

                                                return Promise.resolve();
                                            },
                                        },
                                    ]}
                                >
                                    <InputNumber
                                        style={{ width: '100%' }}
                                        addonAfter={selectAfter}
                                        formatter={(value) => formatGiamGia(value)}
                                        parser={(value) => value.replace(/[^\d]/g, '')} // Chỉ giữ lại số
                                    />
                                </Form.Item>
                            </Col>
                            <Col span={1}></Col>
                            <Col span={12}>
                                <Form.Item
                                    label="Số lượng:"
                                    name="quantity"
                                    initialValue={reacord.quantity}
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Vui lòng nhập số lượng!',
                                        },
                                    ]}
                                >
                                    <Input
                                        style={{ width: '100%' }}
                                        onChange={(e) => {
                                            // Chỉ giữ lại số và loại bỏ dấu cách ở đầu và cuối
                                            const newValue = e.target.value.replace(/[^\d]/g, '');
                                            form.setFieldsValue({
                                                quantity: newValue,
                                            });
                                        }}
                                        onBlur={(e) => {
                                            // Kiểm tra số nguyên và không có dấu cách ở đầu và cuối
                                            const value = e.target.value;
                                            const intValue = parseInt(value, 10);

                                            if (isNaN(intValue) || value.includes(' ') || value[0] === ' ' || value[value.length - 1] === ' ') {
                                                form.setFields([
                                                    {
                                                        name: 'quantity',
                                                        errors: ['Vui lòng nhập số nguyên và không có dấu cách ở đầu và cuối!'],
                                                    },
                                                ]);
                                            }
                                        }}
                                    />
                                </Form.Item>
                            </Col>
                        </Row>
                        <Row>
                            <Col span={11}>
                                <Form.Item label="Ngày bắt đầu:" name="startTime" initialValue={dayjs(reacord.startTime)}

                                    rules={[{ required: true, message: 'Vui lòng nhập ngày bắt đầu!' }]}>
                                    <DatePicker style={{ width: '100%' }} showTime format="HH:mm - DD/MM/YYYY" />
                                </Form.Item>
                            </Col>
                            <Col span={1}></Col>
                            <Col span={12}>
                                <Form.Item
                                    label="Ngày kết thúc:"
                                    name="endTime"
                                    initialValue={dayjs(reacord.endTime)}
                                    rules={[
                                        { required: true, message: 'Vui lòng nhập ngày kết thúc!' },
                                        ({ getFieldValue }) => ({
                                            validator(_, endDate) {
                                                const startDate = getFieldValue('startTime');

                                                //const currentDate = dayjs();

                                                if (!startDate || !endDate) {
                                                    // Nếu chưa có giá trị, không validate
                                                    return Promise.resolve();
                                                }

                                                // if (dayjs(startDate).isAfter(endDate)) {
                                                //     // Ngày kết thúc không được trước ngày bắt đầu
                                                //     return Promise.reject(new Error('Ngày kết thúc phải sau ngày bắt đầu!'));
                                                // }

                                                // if (dayjs(endDate).isBefore(currentDate, 'day')) {
                                                //     // Ngày kết thúc không được là ngày hiện tại hoặc ngày quá khứ
                                                //     return Promise.reject(new Error('Ngày kết thúc không được là ngày hiện tại hoặc ngày quá khứ!'));
                                                // }

                                                return Promise.resolve();
                                            },
                                        }),
                                    ]}
                                >
                                    <DatePicker style={{ width: '100%' }} showTime format="HH:mm - DD/MM/YYYY" />
                                </Form.Item>
                            </Col>
                        </Row>
                        <Row>
                            <Col span={11}>
                                <Form.Item
                                    label="Đơn Tối Thiểu:"
                                    name="minimumOrder"
                                    initialValue={reacord.minimumOrder}
                                    rules={[
                                        {
                                            required: false,
                                            type: 'number',
                                            min: 1,
                                            message: 'Vui lòng nhập tối thiểu!',
                                        },
                                        {
                                            validator: (_, value) => {
                                                const stringValue = String(value);
                                                if (!value) {
                                                    return Promise.resolve();
                                                }
                                                if (/^\s|\s$/.test(stringValue)) {
                                                    return Promise.reject('Vui lòng nhập số nguyên và không có dấu cách ở đầu và cuối!');
                                                }

                                                const intValue = parseInt(stringValue, 10);

                                                if (isNaN(intValue)) {
                                                    return Promise.reject('Vui lòng nhập số nguyên!');
                                                }

                                                return Promise.resolve();
                                            },
                                        },
                                    ]}
                                >
                                    <InputNumber
                                        style={{ width: '100%' }}
                                        formatter={(value) => formatNumberToiThieu(value)}
                                        parser={(value) => value.replace(/[^\d]/g, '')} // Chỉ giữ lại số
                                    />
                                </Form.Item>
                            </Col>
                            <Col span={1}></Col>
                            <Col span={12}>
                                <Form.Item label="Giảm tối đa:" name="minimize" initialValue={reacord.minimize}
                                    rules={[
                                        {
                                            required: false,
                                            type: 'number',
                                            min: 1,
                                            message: 'Vui lòng nhập giảm tối đa!'
                                        },
                                        {
                                            validator: (_, value) => {
                                                const stringValue = String(value);
                                                if (!value) {
                                                    return Promise.resolve();
                                                }
                                                if (/^\s|\s$/.test(stringValue)) {
                                                    return Promise.reject('Vui lòng nhập số nguyên và không có dấu cách ở đầu và cuối!');
                                                }

                                                const intValue = parseInt(stringValue, 10);

                                                if (isNaN(intValue)) {
                                                    return Promise.reject('Vui lòng nhập số nguyên!');
                                                }

                                                return Promise.resolve();
                                            },
                                        },
                                    ]}>
                                    <InputNumber
                                        style={{ width: '100%' }}
                                        formatter={(value) => formatNumberToiDa(value)}
                                        parser={(value) => value.replace(/[^\d]/g, '')} // Chỉ giữ lại số
                                    />
                                </Form.Item>
                            </Col>
                        </Row>


                        <Form.Item label="Ghi chú:" name="describe" initialValue={reacord.describe}>
                            <TextArea rows={4} placeholder="Nhập ghi chú..." />
                        </Form.Item>
                        {/* 
                        <Form.Item label="Trạng thái:" name="deleted"  >
                            <Radio.Group name="radiogroup" style={{ float: 'left' }}>
                                <Radio >Sắp diễn ra</Radio>
                                <Radio >Đang diễn ra</Radio>
                                <Radio >Sắp hết hạn</Radio>
                                <Radio >Hết hạn</Radio>
                                <Radio >Đã hết</Radio>
                                <Radio >Hủy bỏ</Radio>
                            </Radio.Group>
                        </Form.Item> */}
                    </Form>
                </Modal>
            </Col>

        </Row >
    );
};

