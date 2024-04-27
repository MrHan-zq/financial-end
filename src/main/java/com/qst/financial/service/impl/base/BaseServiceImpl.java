package com.qst.financial.service.impl.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.qst.financial.dao.mapper.base.BaseMapper;
import com.qst.financial.modle.base.PoModel;
import com.qst.financial.service.base.BaseService;
import com.qst.financial.sql.WherePrams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@SuppressWarnings("all")
@Service
public class BaseServiceImpl<T extends PoModel, PK extends Serializable> implements BaseService<T,PK> {
	@Autowired
	private BaseMapper<PoModel, Serializable> baseMapper;
	@Override
	public int addLocal(PoModel PoModel) {
		return baseMapper.addLocal(PoModel);
	}

	@Override
	public int add(PoModel PoModel) {
		// TODO Auto-generated method stub
		return baseMapper.add(PoModel);
	}
	@Override
	public int addByMap(Map<String, Object> map){
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
	    return baseMapper.addByMap(entityClass,map);
	}
	@Override
	public T get(Serializable id) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return (T) baseMapper.get(entityClass,id);
	}

	@Override
	public Serializable getField(Serializable id, String fileName) {
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.getField(entityClass,id, fileName);
	}

	@Override
	public T get(WherePrams where) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return (T) baseMapper.get(entityClass,where);
	}

	@Override
	public Serializable getFile(WherePrams where, String fileName) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.getFile(entityClass,where, fileName);
	}

	@Override
	public List<T> list(WherePrams where) {
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return (List<T>) baseMapper.list(entityClass,where);
	}

	@Override
	public Serializable[] listFile(WherePrams where, String fileName) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.listFile(entityClass,where, fileName);
	}

	@Override
	public List<Map<String, Serializable>> listFiles(WherePrams where, String[] files) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.listFiles(entityClass,where, files);
	}

	@Override
	public int updateLocal(PoModel PoModel) {
		// TODO Auto-generated method stub
		return baseMapper.updateLocal(PoModel);
	}

	@Override
	public int update(PoModel PoModel) {
		// TODO Auto-generated method stub
		return baseMapper.update(PoModel);
	}

	@Override
	public int updateLocal(PoModel PoModel, WherePrams where) {
		// TODO Auto-generated method stub
		return baseMapper.update(PoModel, where);
	}

	@Override
	public int update(PoModel PoModel, WherePrams where) {
		// TODO Auto-generated method stub
		return baseMapper.update(PoModel, where);
	}

	@Override
	public int del(Serializable id) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.del(entityClass,id);
	}

	@Override
	public int del(WherePrams where) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.del(entityClass,where);
	}

	@Override
	public List<Map<String, Object>> listBySql(String sql) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.listBySql(entityClass,sql);
	}

	@Override
	public int excuse(String sql) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.excuse(entityClass,sql);
	}

	@Override
	public long count(WherePrams where) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.count(entityClass,where);
	}

	@Override
	public long size() {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.size(entityClass);
	}

	@Override
	public boolean isExist(PoModel PoModel) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.isExist(entityClass,PoModel);
	}

	@Override
	public boolean isExist(WherePrams where) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.isExist(entityClass,where);
	}

	@Override
	public List<T> in(String fileName, Serializable[] values) {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return (List<T>) baseMapper.in(entityClass,fileName, values);
	}

	@Override
	public long nextId() {
		// TODO Auto-generated method stub
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return baseMapper.nextId(entityClass);
	}
	@Override
	public List<T> listPage(WherePrams where,String limit){
		Class entityClass = null;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    Type type = genericSuperclass.getActualTypeArguments()[0];
	    if (type instanceof Class) {
	      entityClass = (Class<T>) type;
	    } else if (type instanceof ParameterizedType) {
	      entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
	    }
		return (List<T>) baseMapper.listPage(entityClass,where,limit);
	}
}
