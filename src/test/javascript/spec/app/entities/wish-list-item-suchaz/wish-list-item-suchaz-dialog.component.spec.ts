/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { WishListItemSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz-dialog.component';
import { WishListItemSuchazService } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz.service';
import { WishListItemSuchaz } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz.model';
import { WishListSuchazService } from '../../../../../../main/webapp/app/entities/wish-list-suchaz';
import { ItemSuchazService } from '../../../../../../main/webapp/app/entities/item-suchaz';

describe('Component Tests', () => {

    describe('WishListItemSuchaz Management Dialog Component', () => {
        let comp: WishListItemSuchazDialogComponent;
        let fixture: ComponentFixture<WishListItemSuchazDialogComponent>;
        let service: WishListItemSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [WishListItemSuchazDialogComponent],
                providers: [
                    WishListSuchazService,
                    ItemSuchazService,
                    WishListItemSuchazService
                ]
            })
            .overrideTemplate(WishListItemSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WishListItemSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WishListItemSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new WishListItemSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.wishListItem = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'wishListItemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new WishListItemSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.wishListItem = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'wishListItemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
