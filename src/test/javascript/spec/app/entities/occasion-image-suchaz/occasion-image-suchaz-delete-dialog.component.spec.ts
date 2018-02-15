/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { OccasionImageSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz-delete-dialog.component';
import { OccasionImageSuchazService } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz.service';

describe('Component Tests', () => {

    describe('OccasionImageSuchaz Management Delete Component', () => {
        let comp: OccasionImageSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<OccasionImageSuchazDeleteDialogComponent>;
        let service: OccasionImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [OccasionImageSuchazDeleteDialogComponent],
                providers: [
                    OccasionImageSuchazService
                ]
            })
            .overrideTemplate(OccasionImageSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OccasionImageSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OccasionImageSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
