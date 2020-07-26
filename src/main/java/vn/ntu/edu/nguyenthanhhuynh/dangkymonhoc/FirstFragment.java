package vn.ntu.edu.nguyenthanhhuynh.dangkymonhoc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class FirstFragment extends Fragment implements MyDatePicker.OnMyDateChangeListener {

    public static final String KEY = "key";

    EditText edtHoTenHS, edtNgaySinh, edtSDT, edtDiaChi;
    ImageView imgvDate;

    RadioGroup rdgGioHoc;
    RadioButton rdbSang, rdbChieu, rdbToi;
    Button button_first;

    Spinner spnKhoaHoc;

    String [] arrayKhoaHoc;
    ArrayAdapter adapterKhoaHoc;

    NavController navController;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                nhanThongTin();
            }
        });

        addView(view);
        addEvent();
    }

    private void addView(View view){

        edtHoTenHS = view.findViewById(R.id.edtHoTenHS);
        edtNgaySinh = view.findViewById(R.id.edtNgaySinh);
        edtSDT = view.findViewById(R.id.edtSDT);
        edtDiaChi = view.findViewById(R.id.edtDiaChi);

        rdgGioHoc = view.findViewById(R.id.rdgGioHoc);
        rdbSang = view.findViewById(R.id.rdbSang);
        rdbChieu = view.findViewById(R.id.rdbChieu);
        rdbToi = view.findViewById(R.id.rdbToi);

        button_first = view.findViewById(R.id.button_first);

        spnKhoaHoc = view.findViewById(R.id.spnKhoaHoc);
        arrayKhoaHoc = view.getResources().getStringArray(R.array.khoahoc);
        adapterKhoaHoc = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayKhoaHoc);
        spnKhoaHoc.setAdapter(adapterKhoaHoc);

        imgvDate = view.findViewById(R.id.imgvDate);

        navController = NavHostFragment.findNavController(FirstFragment.this);
    }

    private void addEvent(){

        //Sử dụng DatePicker để nhận thông tin ngày, tháng, năm khi nhấn vào igmNgaySinh
        imgvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatePicker myDatePicker = new MyDatePicker(getActivity(), Calendar.getInstance(),
                        FirstFragment.this);
                myDatePicker.showMyDatePicker();
            }
        });

        spnKhoaHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String dichVu = adapterDichVu.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
    }

    //Phương thức nhận thông tin của Khách Hàng
    private void nhanThongTin(){
        //Khai báo biến
        String hoTenHS = "";
        String ngaySinh = "";
        String SDT = "";
        String diaChi = "";
        String gioHoc = "";
        String khoaHoc = "";

        //Nhận Thông tin của khách hàng từ textView
        hoTenHS = edtHoTenHS.getText().toString();
        ngaySinh =  edtNgaySinh.getText().toString();
        SDT = edtSDT.getText().toString();
        diaChi = edtDiaChi.getText().toString();

        //Nhận Thông tin PP thanh toán từ radioButton
        switch (rdgGioHoc.getCheckedRadioButtonId()){
            case R.id.rdbSang:
                gioHoc = rdbSang.getText().toString();
                break;
            case R.id.rdbChieu:
                gioHoc = rdbChieu.getText().toString();
                break;
            case R.id.rdbToi:
                gioHoc = rdbToi.getText().toString();
                break;
            default:
                gioHoc = "Lỗi !";
                break;
        }

        //Nhận Thông tin dịch vụ từ Spinner
        khoaHoc = spnKhoaHoc.getSelectedItem().toString();

        //==>Thông báo dạng Toast
        //Toast.makeText(getContext(), hoTen + ngaySinh + SDT + diaChi + PPThanhToan + dichVu, Toast.LENGTH_LONG).show();

        //Sử dụng StringBuider nối chuỗi
        StringBuilder builder = new StringBuilder();
        builder.append("Chúc mừng bạn: ").append(hoTenHS)
                .append("\nSinh ngày: ").append(ngaySinh)
                .append("\nĐã đăng ký thành công khóa học: ")
                .append("\n ").append(khoaHoc)
                .append("\nGiờ học: ").append(gioHoc)
                .append("\nChúng tôi sẽ liên lạc với bạn theo số điện thoại:")
                .append("\n ").append(SDT);

        //Sử dụng Bundle để nhận giá trị từ builder (sử dụng biến KEY)
        Bundle bundle = new Bundle();
        bundle.putCharSequence(KEY, builder.toString());

        //Nhận giá trị cho SecondFragment, chuyển từ giao diện 1 sang giao diện 2 khi nhấn nut
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }

    @Override
    public void dateUpdate(Calendar calendar) {
        StringBuilder builder = new StringBuilder();

        //Định dạng: Ngày/Tháng/Năm
        StringBuilder abc = builder
                .append(calendar.get(calendar.DAY_OF_MONTH))
                .append("/")
                .append(calendar.get(calendar.MONTH) + 1)
                .append("/")
                .append(calendar.get(calendar.YEAR));

        //Hiển thị ngày/tháng/năm lên editTextDate
        edtNgaySinh.setText(builder.toString());
    }

}