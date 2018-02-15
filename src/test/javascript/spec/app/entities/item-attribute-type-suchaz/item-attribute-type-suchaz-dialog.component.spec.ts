/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemAttributeTypeSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/item-attribute-type-suchaz/item-attribute-type-suchaz-dialog.component';
import { ItemAttributeTypeSuchazService } from '../../../../../../main/webapp/app/entities/item-attribute-type-suchaz/item-attribute-type-suchaz.service';
import { ItemAttributeTypeSuchaz } from '../../../../../../main/webapp/app/entities/item-attribute-type-suchaz/item-attribute-type-suchaz.model';
import { CategorySuchazService } from '../../../../../../main/webapp/app/entities/category-suchaz';

describe('Component Tests', () => {

    describe('ItemAttributeTypeSuchaz Management Dialog Component', () => {
        let comp: ItemAttributeTypeSuchazDialogComponent;
        let fixture: ComponentFixture<ItemAttributeTypeSuchazDialogComponent>;
        let service: ItemAttributeTypeSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemAttributeTypeSuchazDialogComponent],
                providers: [
                    CategorySuchazService,
                    ItemAttributeTypeSuchazService
                ]
            })
            .overrideTemplate(ItemAttributeTypeSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemAttributeTypeSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemAttributeTypeSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ItemAttributeTypeSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.itemAttributeType = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'itemAttributeTypeListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ItemAttributeTypeSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.itemAttributeType = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'itemAttributeTypeListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
