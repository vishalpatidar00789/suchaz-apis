/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { CategorySuchazDialogComponent } from '../../../../../../main/webapp/app/entities/category-suchaz/category-suchaz-dialog.component';
import { CategorySuchazService } from '../../../../../../main/webapp/app/entities/category-suchaz/category-suchaz.service';
import { CategorySuchaz } from '../../../../../../main/webapp/app/entities/category-suchaz/category-suchaz.model';

describe('Component Tests', () => {

    describe('CategorySuchaz Management Dialog Component', () => {
        let comp: CategorySuchazDialogComponent;
        let fixture: ComponentFixture<CategorySuchazDialogComponent>;
        let service: CategorySuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [CategorySuchazDialogComponent],
                providers: [
                    CategorySuchazService
                ]
            })
            .overrideTemplate(CategorySuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CategorySuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CategorySuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CategorySuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.category = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'categoryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CategorySuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.category = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'categoryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
