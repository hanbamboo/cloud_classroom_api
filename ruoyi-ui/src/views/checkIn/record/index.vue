<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="二维码签到、位置签到、直接签到、密码签到、人脸识别签到字典" prop="checkinId">
        <el-select v-model="queryParams.checkinId" placeholder="请选择二维码签到、位置签到、直接签到、密码签到、人脸识别签到字典" clearable>
          <el-option
            v-for="dict in dict.type.class_checkin_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="学生id" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="签到时间" prop="checkinTime">
        <el-date-picker clearable
          v-model="queryParams.checkinTime"
          type="date"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择签到时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="已签到、未签到、迟到字典" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择已签到、未签到、迟到字典" clearable>
          <el-option
            v-for="dict in dict.type.class_checkin_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="签到地点" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入签到地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="签到设备" prop="device">
        <el-input
          v-model="queryParams.device"
          placeholder="请输入签到设备"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['checkIn:record:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['checkIn:record:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['checkIn:record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['checkIn:record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="id" />
      <el-table-column label="二维码签到、位置签到、直接签到、密码签到、人脸识别签到字典" align="center" prop="checkinId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.class_checkin_type" :value="scope.row.checkinId"/>
        </template>
      </el-table-column>
      <el-table-column label="学生id" align="center" prop="studentId" />
      <el-table-column label="签到时间" align="center" prop="checkinTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkinTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="已签到、未签到、迟到字典" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.class_checkin_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="签到地点" align="center" prop="location" />
      <el-table-column label="签到备注" align="center" prop="remark" />
      <el-table-column label="签到设备" align="center" prop="device" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['checkIn:record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['checkIn:record:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改签到明细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="二维码签到、位置签到、直接签到、密码签到、人脸识别签到字典" prop="checkinId">
          <el-select v-model="form.checkinId" placeholder="请选择二维码签到、位置签到、直接签到、密码签到、人脸识别签到字典">
            <el-option
              v-for="dict in dict.type.class_checkin_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学生id" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生id" />
        </el-form-item>
        <el-form-item label="签到时间" prop="checkinTime">
          <el-date-picker clearable
            v-model="form.checkinTime"
            type="date"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择签到时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="已签到、未签到、迟到字典" prop="status">
          <el-select v-model="form.status" placeholder="请选择已签到、未签到、迟到字典">
            <el-option
              v-for="dict in dict.type.class_checkin_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="签到地点" prop="location">
          <el-input v-model="form.location" placeholder="请输入签到地点" />
        </el-form-item>
        <el-form-item label="签到备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入签到备注" />
        </el-form-item>
        <el-form-item label="签到设备" prop="device">
          <el-input v-model="form.device" placeholder="请输入签到设备" />
        </el-form-item>
        <el-form-item label="${comment}" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入${comment}" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/checkIn/record";

export default {
  name: "Record",
  dicts: ['class_checkin_type', 'class_checkin_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 签到明细表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        checkinId: null,
        studentId: null,
        checkinTime: null,
        status: null,
        location: null,
        device: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        checkinId: [
          { required: true, message: "二维码签到、位置签到、直接签到、密码签到、人脸识别签到字典不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询签到明细列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        checkinId: null,
        studentId: null,
        checkinTime: null,
        status: null,
        location: null,
        remark: null,
        device: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加签到明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改签到明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除签到明细编号为"' + ids + '"的数据项？').then(function() {
        return delRecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('checkIn/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
