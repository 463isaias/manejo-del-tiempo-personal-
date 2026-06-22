import { useEffect, useMemo, useState } from 'react';

const API_BASE = 'http://localhost:8081/api';

const sections = {
  usuarios: {
    label: 'Usuarios',
    endpoint: 'usuarios',
    fields: [
      { name: 'nombre', label: 'Nombre', type: 'text' },
      { name: 'email', label: 'Email', type: 'email' },
      { name: 'password', label: 'Contraseña', type: 'password' }
    ]
  },
  tareas: {
    label: 'Tareas',
    endpoint: 'tareas',
    fields: [
      { name: 'nombre', label: 'Nombre', type: 'text' },
      { name: 'descripcion', label: 'Descripción', type: 'text' },
      { name: 'fechaInicio', label: 'Fecha inicio', type: 'date' },
      { name: 'fechaFin', label: 'Fecha fin', type: 'date' },
      { name: 'estado', label: 'Estado', type: 'text' }
    ]
  },
  categorias: {
    label: 'Categorías',
    endpoint: 'categorias',
    fields: [
      { name: 'nombre', label: 'Nombre', type: 'text' }
    ]
  },
  registros: {
    label: 'Registros',
    endpoint: 'registros',
    fields: [
      { name: 'fecha', label: 'Fecha', type: 'date' },
      { name: 'duracion', label: 'Duración (min)', type: 'number' }
    ]
  },
  recordatorios: {
    label: 'Recordatorios',
    endpoint: 'recordatorios',
    fields: [
      { name: 'mensaje', label: 'Mensaje', type: 'text' },
      { name: 'fechaHora', label: 'Fecha y hora', type: 'datetime-local' },
      { name: 'tipo', label: 'Tipo', type: 'text' }
    ]
  }
};

const buildEmptyForm = (sectionKey) => {
  const section = sections[sectionKey];
  return section.fields.reduce((acc, field) => {
    acc[field.name] = '';
    return acc;
  }, {});
};

function App() {
  const [activeSection, setActiveSection] = useState('usuarios');
  const [items, setItems] = useState([]);
  const [form, setForm] = useState(buildEmptyForm('usuarios'));
  const [editItem, setEditItem] = useState(null);
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(false);

  const section = useMemo(() => sections[activeSection], [activeSection]);

  useEffect(() => {
    setForm(buildEmptyForm(activeSection));
    setEditItem(null);
    fetchItems();
  }, [activeSection]);

  const fetchItems = async () => {
    setLoading(true);
    try {
      const response = await fetch(`${API_BASE}/${section.endpoint}`);
      const data = await response.json();
      setItems(Array.isArray(data) ? data : []);
    } catch (error) {
      setMessage('No se pudo cargar la información.');
    } finally {
      setLoading(false);
    }
  };

  const getItemId = (item) => {
    if (!item) return null;
    return (
      item.id ||
      item.usuarioId ||
      item.tareaId ||
      item.categoriaId ||
      item.claseId ||
      item.recordatorioId ||
      item.registroId ||
      null
    );
  };

  const handleChange = (field, value) => {
    setForm((prev) => ({ ...prev, [field]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const method = editItem ? 'PUT' : 'POST';
    const id = getItemId(editItem);
    const url = editItem
      ? `${API_BASE}/${section.endpoint}/${id}`
      : `${API_BASE}/${section.endpoint}`;

    try {
      const payload = { ...form };
      await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });
      setMessage(editItem ? 'Elemento actualizado.' : 'Elemento creado.');
      setForm(buildEmptyForm(activeSection));
      setEditItem(null);
      fetchItems();
    } catch (error) {
      setMessage('Error al guardar el elemento.');
    }
  };

  const handleEdit = (item) => {
    const editable = Object.keys(form).reduce((acc, key) => {
      const value = item[key] ?? '';
      acc[key] = value;
      return acc;
    }, {});
    setForm(editable);
    setEditItem(item);
  };

  const handleDelete = async (item) => {
    const id = item.id ?? item[`${activeSection.slice(0, -1)}Id`];
    if (!id) return;
    try {
      await fetch(`${API_BASE}/${section.endpoint}/${id}`, { method: 'DELETE' });
      setMessage('Elemento eliminado.');
      fetchItems();
    } catch (error) {
      setMessage('No se pudo eliminar el elemento.');
    }
  };

  const sectionLabel = section.label;

  return (
    <div className="app-shell">
      <header className="topbar">
        <div>
          <h1>Manejo de Tiempo</h1>
          <p>Frontend responsivo conectado a tu backend en <strong>http://localhost:8081</strong></p>
        </div>
        <nav className="nav-bar">
          {Object.entries(sections).map(([key, value]) => (
            <button
              key={key}
              className={key === activeSection ? 'nav-button active' : 'nav-button'}
              onClick={() => setActiveSection(key)}
            >
              {value.label}
            </button>
          ))}
        </nav>
      </header>

      <main className="content">
        <section className="panel">
          <div className="panel-header">
            <h2>{sectionLabel}</h2>
            <span>{items.length} elementos</span>
          </div>

          {message && <div className="notification">{message}</div>}

          <div className="layout-grid">
            <div className="panel-card">
              <h3>{editItem ? 'Editar elemento' : 'Nuevo elemento'}</h3>
              <form onSubmit={handleSubmit} className="entity-form">
                {section.fields.map((field) => (
                  <label key={field.name}>
                    <span>{field.label}</span>
                    <input
                      type={field.type}
                      value={form[field.name] || ''}
                      onChange={(e) => handleChange(field.name, e.target.value)}
                      required
                    />
                  </label>
                ))}
                <div className="form-actions">
                  <button type="submit">{editItem ? 'Guardar cambios' : 'Crear'}</button>
                  {editItem && (
                    <button type="button" className="secondary" onClick={() => {
                      setEditItem(null);
                      setForm(buildEmptyForm(activeSection));
                    }}>
                      Cancelar
                    </button>
                  )}
                </div>
              </form>
            </div>

            <div className="panel-card">
              <h3>Lista</h3>
              {loading ? (
                <p>Cargando...</p>
              ) : items.length === 0 ? (
                <p>No hay elementos aún.</p>
              ) : (
                <div className="list-grid">
                  {items.map((item) => {
                    const itemId = item.id ?? item[`${activeSection.slice(0, -1)}Id`];
                    return (
                      <article key={itemId || JSON.stringify(item)} className="list-item">
                        <div>
                          <strong>ID:</strong> {itemId ?? 'N/A'}
                        </div>
                        {section.fields.map((field) => (
                          <div key={field.name}>
                            <strong>{field.label}:</strong> {item[field.name] ?? '---'}
                          </div>
                        ))}
                        <div className="item-actions">
                          <button onClick={() => handleEdit(item)}>Editar</button>
                          <button className="danger" onClick={() => handleDelete(item)}>Eliminar</button>
                        </div>
                      </article>
                    );
                  })}
                </div>
              )}
            </div>
          </div>
        </section>
      </main>
    </div>
  );
}

export default App;
