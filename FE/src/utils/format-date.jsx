import dayjs from 'dayjs';

const FormatDate = (date, format = 'hh:mm:ss - DD/MM/YYYY') => {
    if (!date) return '';
    return dayjs(date).format(format);
};

export default FormatDate;