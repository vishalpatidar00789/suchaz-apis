/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/item-suchaz/item-suchaz-dialog.component';
import { ItemSuchazService } from '../../../../../../main/webapp/app/entities/item-suchaz/item-suchaz.service';
import { ItemSuchaz } from '../../../../../../main/webapp/app/entities/item-suchaz/item-suchaz.model';
import { OfferSuchazService } from '../../../../../../main/webapp/app/entities/offer-suchaz';
import { CategorySuchazService } from '../../../../../../main/webapp/app/entities/category-suchaz';
import { VendorSuchazService } from '../../../../../../main/webapp/app/entities/vendor-suchaz';
import { StoreSuchazService } from '../../../../../../main/webapp/app/entities/store-suchaz';

describe('Component Tests', () => {

    describe('ItemSuchaz Management Dialog Component', () => {
        let comp: ItemSuchazDialogComponent;
        let fixture: ComponentFixture<ItemSuchazDialogComponent>;
        let service: ItemSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemSuchazDialogComponent],
                providers: [
                    OfferSuchazService,
                    CategorySuchazService,
                    VendorSuchazService,
                    StoreSuchazService,
                    ItemSuchazService
                ]
            })
            .overrideTemplate(ItemSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ItemSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.item = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'itemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ItemSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.item = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'itemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
