/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemReviewSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz-dialog.component';
import { ItemReviewSuchazService } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz.service';
import { ItemReviewSuchaz } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz.model';
import { ItemSuchazService } from '../../../../../../main/webapp/app/entities/item-suchaz';

describe('Component Tests', () => {

    describe('ItemReviewSuchaz Management Dialog Component', () => {
        let comp: ItemReviewSuchazDialogComponent;
        let fixture: ComponentFixture<ItemReviewSuchazDialogComponent>;
        let service: ItemReviewSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemReviewSuchazDialogComponent],
                providers: [
                    ItemSuchazService,
                    ItemReviewSuchazService
                ]
            })
            .overrideTemplate(ItemReviewSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemReviewSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemReviewSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ItemReviewSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.itemReview = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'itemReviewListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ItemReviewSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.itemReview = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'itemReviewListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
