<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入课程名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程描述" prop="desp">
        <el-input
          v-model="queryParams.desp"
          placeholder="请输入课程描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="必修课、选修课、实验课字典" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择必修课、选修课、实验课字典" clearable>
          <el-option
            v-for="dict in dict.type.class_course_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="上课时间" prop="time">
        <el-date-picker clearable
          v-model="queryParams.time"
          type="date"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择上课时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="教师id" prop="teacherId">
        <el-input
          v-model="queryParams.teacherId"
          placeholder="请输入教师id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="授课学生数" prop="capacity">
        <el-input
          v-model="queryParams.capacity"
          placeholder="请输入授课学生数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="授课中、已结束、已取消字典" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择授课中、已结束、已取消字典" clearable>
          <el-option
            v-for="dict in dict.type.class_course_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="课程标签" prop="tags">
        <el-input
          v-model="queryParams.tags"
          placeholder="请输入课程标签"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开课学院" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入开课学院"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程封面" prop="cover">
        <el-input
          v-model="queryParams.cover"
          placeholder="请输入课程封面"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程学分" prop="credits">
        <el-input
          v-model="queryParams.credits"
          placeholder="请输入课程学分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="授课课时" prop="period">
        <el-input
          v-model="queryParams.period"
          placeholder="请输入授课课时"
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
          v-hasPermi="['course:info:add']"
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
          v-hasPermi="['course:info:edit']"
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
          v-hasPermi="['course:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['course:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="" align="center" prop="id" />
      <el-table-column label="课程名" align="center" prop="ntimeame" />
      <el-table-column label="课程描述" align="center" prop="desp" />
      <el-table-column label="必修课、选修课、实验课字典" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.class_course_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="上课时间" align="center" prop="time" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.time, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="教师id" align="center" prop="teacherId" />
      <el-table-column label="授课学生数" align="center" prop="capacity" />
      <el-table-column label="授课中、已结束、已取消字典" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.class_course_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="课程标签" align="center" prop="tags" />
      <el-table-column label="开课学院" align="center" prop="deptId" />
      <el-table-column label="课程封面" align="center" prop="cover" />
      <el-table-column label="课程学分" align="center" prop="credits" />
      <el-table-column label="授课课时" align="center" prop="period" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['course:info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['course:info:remove']"
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

    <!-- 添加或修改课程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名" prop="name">
          <el-input v-model="form.name" placeholder="请输入课程名" />
        </el-form-item>
        <el-form-item label="课程描述" prop="desp">
          <el-input v-model="form.desp" placeholder="请输入课程描述" />
        </el-form-item>
        <el-form-item label="必修课、选修课、实验课字典" prop="type">
          <el-select v-model="form.type" placeholder="请选择必修课、选修课、实验课字典">
            <el-option
              v-for="dict in dict.type.class_course_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上课时间" prop="time">
          <el-date-picker clearable
            v-model="form.time"
            type="date"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择上课时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="授课学生数" prop="capacity">
          <el-input v-model="form.capacity" placeholder="请输入授课学生数" />
        </el-form-item>
        <el-form-item label="开课学院" prop="capacity">
          <el-input v-model="form.deptId" placeholder="请输入开课学院" />
        </el-form-item>
        <el-form-item label="授课中、已结束、已取消字典" prop="status">
          <el-select v-model="form.status" placeholder="请选择授课中、已结束、已取消字典">
            <el-option
              v-for="dict in dict.type.class_course_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="课程标签" prop="tags">
          <el-input v-model="form.tags" placeholder="请输入课程标签" />
        </el-form-item>
        <el-form-item label="课程封面" prop="cover">
          <el-input v-model="form.cover" placeholder="请输入课程封面" />
        </el-form-item>
        <el-form-item label="课程学分" prop="credits">
          <el-input v-model="form.credits" placeholder="请输入课程学分" />
        </el-form-item>
        <el-form-item label="授课课时" prop="period">
          <el-input v-model="form.period" placeholder="请输入授课课时" />
        </el-form-item>
        <el-form-item label="标志" prop="delFlag">
          <el-select v-model="form.delFlag" placeholder="请选择标志">
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入" />
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
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/course/info";

export default {
  name: "Info",
  dicts: ['sys_yes_no', 'class_course_status', 'class_course_type'],
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
      // 课程表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        desp: null,
        type: null,
        time: null,
        teacherId: null,
        capacity: null,
        status: null,
        tags: null,
        deptId: null,
        cover: null,
        credits: null,
        period: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询课程列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
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
        name: null,
        desp: null,
        type: null,
        time: null,
        teacherId: null,
        capacity: null,
        status: null,
        tags: null,
        deptId: null,
        cover: null,
        credits: null,
        period: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null
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
      this.title = "添加课程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除课程编号为"' + ids + '"的数据项？').then(function() {
        return delInfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('course/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
