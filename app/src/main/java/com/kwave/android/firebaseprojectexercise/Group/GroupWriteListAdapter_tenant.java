package com.kwave.android.firebaseprojectexercise.Group;

/**
 * Created by kwave on 2017-07-04.
 */
//
//public class GroupWriteListAdapter_tenant extends RecyclerView.Adapter<GroupWriteListAdapter_tenant.Holder>{
//    private List<MyHomeData> data = new ArrayList<>();
//    private LayoutInflater inflater;
//
//
//    public GroupWriteListAdapter_tenant(List<MyHomeData> data, Context context) {
//        this.data = data;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    public void setData(List<MyHomeData> data){
//        this.data = data;
//    }
//
//    @Override
//    public int getItemCount() {
//
//        return data.size();
//    }
//
//
//
//    @Override
//    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.item_group_write_tenant, parent, false);
//
//        return new Holder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(Holder holder, int position) {
//
//        MyHomeData bbs = data.get(position);
//        holder.setEditGroupWriteNameTenant(bbs.name);
//        holder.setEditGroupWriteCountTenant(bbs.countTenant);
//        holder.setEditGroupWriteDayTenant(bbs.contract);
//        holder.setEditGroupWriteRoomTenant(bbs.room);
//        holder.setPosition(position);
//
//    }
//
//    class Holder extends RecyclerView.ViewHolder implements GroupWriteFragment_tenant.test{
//        FirebaseDatabase database;
//        DatabaseReference bbsRef;
//        private int position;
//        private EditText editGroupWriteNameTenant;
//        private EditText editGroupWriteCountTenant;
//        private EditText editGroupWriteDayTenant;
//        private EditText editGroupWriteRoomTenant;
//
//        public Holder(View v) {
//            super(v);
//            editGroupWriteNameTenant = (EditText) v.findViewById(R.id.editGroupWriteNameTenant);
//            editGroupWriteCountTenant = (EditText) v.findViewById(R.id.editGroupWriteCountTenant);
//            editGroupWriteDayTenant = (EditText) v.findViewById(R.id.editGroupWriteDayTenant);
//            editGroupWriteRoomTenant = (EditText) v.findViewById(R.id.editGroupWriteRoomTenant);
////            v.setOnClickListener(new View.OnClickListener() {
////                 @Override
////                 public void onClick(View v) {
////                    Intent intent = new Intent(v.getContext(), GroupWriteActivity.class);
////
////
////
////                    v.getContext().startActivity(intent);
////                 }
////            });
//
//        }
//        public void setPosition(int position){
//            this.position = position;
//        }
//
//
//        public String getEditGroupName() {
//            return editGroupWriteNameTenant.getText().toString();
//        }
//
//        public String getEditGroupCount() {
//            return editGroupWriteCountTenant.getText().toString();
//        }
//
//        public String getEditGroupDay() {
//            return editGroupWriteDayTenant.getText().toString();
//        }
//
//        public String getEditGroupRoom() {
//            return editGroupWriteRoomTenant.getText().toString();
//        }
//
//
//
//        public void setEditGroupWriteNameTenant(String GroupWriteNameTenant) {
//            editGroupWriteNameTenant.setText(GroupWriteNameTenant);
//        }
//
//        public void setEditGroupWriteCountTenant(int GroupWriteCountTenant) {
//            editGroupWriteCountTenant.setText(GroupWriteCountTenant+"만원");
//        }
//
//        public void setEditGroupWriteDayTenant(String GroupWriteDayTenant) {
//            editGroupWriteDayTenant.setText(GroupWriteDayTenant);
//        }
//        public void setEditGroupWriteRoomTenant(int GroupWriteRoomTenant) {
//            editGroupWriteRoomTenant.setText(GroupWriteRoomTenant+"호");
//        }
//
//
//        @Override
//        public void setTest() {
//            database = FirebaseDatabase.getInstance();
//            bbsRef = database.getReference("남일빌라/세입자 관리/2017/7/세입자 정보/101/");
//            // 데이터 받아 올 변수 만들기
//            int GroupWriteRoomTenant = Integer.parseInt(editGroupWriteNameTenant.getText().toString());
//            String GroupWriteNameTenant = editGroupWriteCountTenant.getText().toString();
//            int GroupWriteCountTenant = Integer.parseInt(editGroupWriteDayTenant.getText().toString());
//            String GroupWriteDayTenant = editGroupWriteRoomTenant.getText().toString();
//
////        Log.i("FBStorage","Upload check ========= 3");
//
//            // 파이어베이스 데이터베이스에 데이터 넣기
//            // 1. 데이터 객체 생성
//            MyHomeData bbs = new MyHomeData(GroupWriteRoomTenant, GroupWriteNameTenant,GroupWriteCountTenant, GroupWriteDayTenant);
//
//            // 이미지가 있으면 이미지 올리기
////        if(imageUri != null){
////            bbs.fileUriString = imageUri.toString();
////        }
//
//            // 2. 입력할 데이터의 키 생성
//            String bbsKey = bbsRef.push().getKey(); // 자동생성된 키를 가져온다
//            // 3. 생성된 키를 레퍼런스로 데이터를 입력
//            //    insert 와 update, delete 는 동일하게 동작
////        bbsRef.child("bbsKey").setValue(bbs.masterNotify);        // 자동생성키로 키를 받아서 입력된다.
//            bbsRef.child("집주인 이름").setValue(bbs.masterName);        // 내가 원하는 부분으로 입력된다.
//            bbsRef.child("집주인 주소").setValue(bbs.masterAddr);
//            bbsRef.child("집주인 계좌번호").setValue(bbs.masterAccount);
//            bbsRef.child("집주인 전화번호").setValue(bbs.masterPhoneNumber);
//            bbsRef.child("분리수거 안내").setValue(bbs.masterTrash);
//            //    update : bbsRef.child(bbsKey).setValue(bbs);
//            //    delete : bbsRef.child(bbsKey).setValue(null);
//            // 데이터 입력후 창 닫기
//        }
//    }
//}
