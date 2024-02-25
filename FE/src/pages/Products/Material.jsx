import React, { useState, useRef, useMemo, useEffect } from 'react';
import { Table, Space, Button, Input, Form, Modal, notification, Radio, Popconfirm, Tag, Switch } from 'antd';
import {
    PlusOutlined,
    RedoOutlined,
    FormOutlined,
    DeleteOutlined,
    SearchOutlined,
} from '@ant-design/icons';
import './Product.css'
import MaterialService from '~/service/MaterialService';
import FormatDate from '~/utils/format-date';

const { TextArea } = Input;

function Material() {

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

    const [materials, setMaterials] = useState([]);
    const [searchName, setSearchName] = useState(null);

    //  Phân trang

    const [pagination, setPagination] = useState({
        current: 1,
        pageSize: 5,
        total: 0,
    });

    const fetchMaterials = async () => {
        try {
            const response = await MaterialService.getAll(
                pagination.current - 1,
                pagination.pageSize,
                searchName,
            );
            setLoading(true);
            console.log('Response:', response);
            console.log('Status:', response.status);
            console.log('Data:', response.data);

            if (response && response.data) {
                const status = response.status || (response.data && response.data.status);

                if (status === 200) {
                    const responseData = response.data;

                    if (Array.isArray(responseData)) {
                        console.log('Response Data:', responseData);
                        const formattedProducers = responseData.map(material => ({
                            key: material.id,
                            id: material.id,
                            code: material.code,
                            name: material.name,
                            ghi_chu: material.ghi_chu,
                            dateCreate: new Date(material.dateCreate).toLocaleString(),
                            dateUpdate: material.dateUpdate ? new Date(material.dateUpdate).toLocaleString() : 'N/A',
                            status: String(material.status),  // Chuyển đổi thành chuỗi 
                        }));
                        setMaterials(formattedProducers);
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
        fetchMaterials();
    }, [pagination.current, pagination.pageSize, searchName]);


    const handleDelete = async (id) => {
        await MaterialService.delete(id).then(() => {

            fetchMaterials();
        }).catch(error => {
            console.error(error);
            notification.error({
                message: 'Thông báo',
                description: 'Đã có lỗi xảy ra!',
            });
        });
    };


    const handleReset = () => {

        setSearchName(null);

        setPagination({
            ...pagination,
            current: 1,
        });
    };

    const handleTableChange = (pagination, filters) => {


        const searchNameFilter = filters?.materialName;
        if (searchNameFilter) {
            setSearchName(searchNameFilter[0]);
        } else {
            setSearchName(null)
        }

        const statusFilter = filters?.deleted;
        const isNoStatusFilter = !statusFilter || statusFilter.length === 0;

        if (!isNoStatusFilter) {
            const isBothStatus = statusFilter.length === 2;

            // setDeleted(isBothStatus ? null : statusFilter[0]);
        } else {
            // setDeleted(null);
        }

    };
    const getColumnSearchProps = (dataIndex) => ({
        filteredValue: dataIndex === 'name' ? [searchName] : dataIndex,
        filterDropdown: ({ setSelectedKeys, selectedKeys, confirm }) => (
            <Input.Search
                placeholder={`Nhập từ khóa...`}
                value={selectedKeys[0]}
                onChange={(e) => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                onSearch={(value) => {
                    setSelectedKeys(value ? [value.trim()] : []);
                    confirm();
                }}
                style={{ display: 'block' }}
            />
        ),
    });

    const columns = [
        {
            title: '#',
            dataIndex: 'key',
            key: 'key',
            width: '5%',
            render: (value, item, index) => (pagination.current - 1) * pagination.pageSize + index + 1
        },

        {
            title: 'Mã',
            dataIndex: 'code',
            key: 'code',
            width: '19%',
        },

        {
            title: 'Tên chất liệu',
            dataIndex: 'name',
            key: 'name',
            width: '20%',
        },
        {
            title: 'Ngày tạo',
            dataIndex: 'dateCreate',
            key: 'dateCreate',
            width: '10%',
        },
        {
            title: 'Ngày sửa',
            dataIndex: 'dateUpdate',
            key: 'dateUpdate',
            width: '14%',
        },
        {
            title: 'Ghi Chú',
            dataIndex: 'ghi_chu',
            key: 'ghi_chu',
            width: '15%',
        },
        {
            title: 'Trạng thái',
            key: 'status',
            dataIndex: 'status',
            width: '16%',
            filters: [
                {
                    text: 'Đang hoạt động',
                    value: true,
                },
                {
                    text: 'Ngừng hoạt động',
                    value: false,
                },
            ],
            onFilter: (value, record) => record.deleted === value,
            render: (text) => (
                text ? <Tag style={{ borderRadius: '4px', fontWeight: '450', padding: '0 4px ' }} color="#108ee9">Đang hoạt động</Tag>
                    : <Tag style={{ borderRadius: '4px', fontWeight: '450', padding: '0 4px ' }} color="#f50">Ngừng hoạt động</Tag>
            )
        },
        {
            title: 'Hành động',
            key: 'action',
            width: '10%',
            render: (record) => {

                return <Space size="middle">
                    <Button type="text"
                        icon={<FormOutlined style={{ color: 'rgb(214, 103, 12)' }} />}
                        onClick={() => showModal("edit", record)} />
                    <Switch
                        size="small"
                        defaultChecked={record.deleted}
                        onClick={() => handleDelete(record.id)}
                    />

                </Space>
            }

        },
    ];

    return (
        <>
            <h3 style={{ marginBottom: '16px', float: 'left', color: '#2123bf' }}>Danh sách chất liệu</h3>

            <Button type="primary"
                icon={<PlusOutlined />}
                onClick={() => showModal("add")}
                style={{ marginBottom: '16px', float: 'right', borderRadius: '2px' }} >
                Thêm mới
            </Button>

            <Button type="primary"
                icon={<RedoOutlined style={{ fontSize: '18px' }} />}
                style={{ marginBottom: '16px', float: 'right', marginRight: '6px', borderRadius: '4px', }}
                onClick={handleReset}
            />

            <Table
                dataSource={materials.map((material, index) => ({
                    ...material,
                    key: index + 1,
                    createdAt: FormatDate(material.createdAt)
                }))}
                columns={columns}
                pagination={{
                    current: pagination.current,
                    pageSize: pagination.pageSize,
                    total: pagination.total,
                    showSizeChanger: true,
                    onChange: (page, pageSize) => setPagination({ ...pagination, current: page, pageSize }),
                    onShowSizeChange: (current, size) => setPagination({ ...pagination, current: 1, pageSize: size }),
                }}
            />

            {open.isModal && <MaterialModal
                isMode={open.isMode}
                reacord={open.reacord || {}}
                materials={materials}
                // sizes={sizes}
                hideModal={hideModal}
                isModal={open.isModal}

                fetchMaterials={fetchMaterials} />}
        </>
    )
};
export default Material;


const MaterialModal = ({ isMode, reacord, hideModal, isModal, fetchMaterials, materials }) => {

    const [form] = Form.useForm();

    const handleCreate = () => {
        form.validateFields().then(async () => {

            const data = form.getFieldsValue();

            await MaterialService.create(data)
                .then(() => {
                    notification.success({
                        message: 'Thông báo',
                        description: 'Thêm mới thành công!',
                    });
                    fetchMaterials();
                    // Đóng modal
                    hideModal();
                })
                .catch(error => {
                    notification.error({
                        message: 'Thông báo',
                        description: 'Thêm mới thất bại!',
                    });
                    console.error(error);
                });

        }).catch(error => {
            console.error(error);
        })

    }
    const handleUpdate = () => {
        console.log('Record ID in handleUpdate:', reacord.id);
        form.validateFields().then(async () => {

            const data = form.getFieldsValue();
            console.log('Record ID in handleUpdate:', reacord.id);
            await MaterialService.update(reacord.id, data)
                .then(() => {
                    notification.success({
                        message: 'Thông báo',
                        description: 'Cập nhật thành công!',
                    });
                    fetchMaterials();
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

    return (

        <Modal
            title={isMode === "edit" ? "Cập nhật chất liệu" : "Thêm mới một chất liệu"}
            open={isModal}
            onOk={isMode === "edit" ? handleUpdate : handleCreate}
            onCancel={hideModal}
            okText={isMode === "edit" ? "Cập nhật" : "Thêm mới"}
            cancelText="Hủy bỏ"
        >
            <Form
                name="wrap"
                labelCol={{ flex: '100px' }}
                labelAlign="left"
                labelWrap
                wrapperCol={{ flex: 1 }}
                colon={false}
                style={{ maxWidth: 600, marginTop: '25px' }}
                form={form}
                initialValues={{ ...reacord }}
            >
                <Form.Item
                    label="Tên:"
                    name="name"
                    rules={[
                        { required: true, message: 'Vui lòng nhập tên nhà cung cấp!' },
                        {
                            validator: (_, value) => {
                                if (!value) {
                                    return Promise.resolve(); // Không thực hiện validate khi giá trị rỗng
                                }

                                // Thêm kiểm tra xem giá trị có phải là chuỗi không
                                if (typeof value !== 'string') {
                                    return Promise.reject('Giá trị không hợp lệ!');
                                }

                                const trimmedValue = value.trim(); // Loại bỏ dấu cách ở đầu và cuối
                                const lowercaseValue = trimmedValue.toLowerCase(); // Chuyển về chữ thường
                                const isDuplicate = materials.some(
                                    (materials) => materials.name.trim().toLowerCase() === lowercaseValue && materials.id !== reacord.id
                                );

                                if (isDuplicate) {
                                    return Promise.reject('Tên chất liệu đã tồn tại!');
                                }

                                // Kiểm tra dấu cách ở đầu và cuối
                                if (/^\s|\s$/.test(value)) {
                                    return Promise.reject('Tên chất liệu không được chứa dấu cách ở đầu và cuối!');
                                }

                                return Promise.resolve();
                            },
                        },
                    ]}
                >
                    <Input placeholder="Nhập tên chất liệu..." />
                </Form.Item>

                <Form.Item label="Ghi chú:" name="ghi_chu">
                    <TextArea rows={4} placeholder="Nhập ghi chú..." />
                </Form.Item>

                <Form.Item label="Trạng thái:" name="status" initialValue="DANG_HOAT_DONG">
                    <Radio.Group name="radiogroup" style={{ float: 'left' }}>
                        <Radio value="DANG_HOAT_DONG">Đang hoạt động</Radio>
                        <Radio value="NGUNG_HOAT_DONG">Ngừng hoạt động</Radio>
                    </Radio.Group>
                </Form.Item>

            </Form>
        </Modal>
    );
};

