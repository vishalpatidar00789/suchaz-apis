/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { BuyLaterListItemSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/buy-later-list-item-suchaz/buy-later-list-item-suchaz-dialog.component';
import { BuyLaterListItemSuchazService } from '../../../../../../main/webapp/app/entities/buy-later-list-item-suchaz/buy-later-list-item-suchaz.service';
import { BuyLaterListItemSuchaz } from '../../../../../../main/webapp/app/entities/buy-later-list-item-suchaz/buy-later-list-item-suchaz.model';
import { BuyLaterListSuchazService } from '../../../../../../main/webapp/app/entities/buy-later-list-suchaz';
import { ItemSuchazService } from '../../../../../../main/webapp/app/entities/item-suchaz';

describe('Component Tests', () => {

    describe('BuyLaterListItemSuchaz Management Dialog Component', () => {
        let comp: BuyLaterListItemSuchazDialogComponent;
        let fixture: ComponentFixture<BuyLaterListItemSuchazDialogComponent>;
        let service: BuyLaterListItemSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [BuyLaterListItemSuchazDialogComponent],
                providers: [
                    BuyLaterListSuchazService,
                    ItemSuchazService,
                    BuyLaterListItemSuchazService
                ]
            })
            .overrideTemplate(BuyLaterListItemSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BuyLaterListItemSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BuyLaterListItemSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new BuyLaterListItemSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.buyLaterListItem = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'buyLaterListItemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new BuyLaterListItemSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.buyLaterListItem = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'buyLaterListItemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
