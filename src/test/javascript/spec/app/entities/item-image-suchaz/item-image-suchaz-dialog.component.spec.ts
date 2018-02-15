/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemImageSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/item-image-suchaz/item-image-suchaz-dialog.component';
import { ItemImageSuchazService } from '../../../../../../main/webapp/app/entities/item-image-suchaz/item-image-suchaz.service';
import { ItemImageSuchaz } from '../../../../../../main/webapp/app/entities/item-image-suchaz/item-image-suchaz.model';
import { ItemSuchazService } from '../../../../../../main/webapp/app/entities/item-suchaz';

describe('Component Tests', () => {

    describe('ItemImageSuchaz Management Dialog Component', () => {
        let comp: ItemImageSuchazDialogComponent;
        let fixture: ComponentFixture<ItemImageSuchazDialogComponent>;
        let service: ItemImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemImageSuchazDialogComponent],
                providers: [
                    ItemSuchazService,
                    ItemImageSuchazService
                ]
            })
            .overrideTemplate(ItemImageSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemImageSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemImageSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ItemImageSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.itemImage = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'itemImageListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ItemImageSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.itemImage = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'itemImageListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
